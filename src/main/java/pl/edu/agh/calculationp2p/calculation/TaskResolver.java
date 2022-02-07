package pl.edu.agh.calculationp2p.calculation;

import pl.edu.agh.calculationp2p.calculationTask.CalculationTask;
import pl.edu.agh.calculationp2p.calculationTask.CalculationTaskIterator;
import pl.edu.agh.calculationp2p.calculationTask.ResultBuilder;
import pl.edu.agh.calculationp2p.state.future.Future;
import pl.edu.agh.calculationp2p.state.proxy.TaskGiver;

import java.util.Optional;

public class TaskResolver extends Thread {

    private TaskGiver taskGiver;
    private CalculationTask calculationTask;

    private final int sleepTime = 100;

    public TaskResolver(TaskGiver taskGiver, CalculationTask calculationTask) {
        this.taskGiver = taskGiver;
        this.calculationTask = calculationTask;
    }

    @Override
    public void run() {
        Future<Optional<Integer>> task = taskGiver.getTask();
        while (true) {
            while(!task.isReady()){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(task.get().isEmpty()){
                task = taskGiver.getTask();
                continue;
            }

            Integer taskId = task.get().get();
            task = taskGiver.getTask();
            Future<Void> observer = taskGiver.observeTask(taskId);
            ResultBuilder resultBuilder = calculationTask.getResultBuilder();
            resultBuilder.reset();
            CalculationTaskIterator iterator = calculationTask.getFragmentOfTheTask(taskId);

            while (iterator.hasNext()) {
                if(observer.isReady())
                    break;
                resultBuilder.performComputation(iterator.getNext());
            }

            if(!observer.isReady())
                taskGiver.finishTask(taskId, resultBuilder.getResult());
        }
    }
}

