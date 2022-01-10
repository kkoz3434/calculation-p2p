package pl.edu.agh.calculationp2p.state.request;

import pl.edu.agh.calculationp2p.state.Progress;
import pl.edu.agh.calculationp2p.state.Servant;
import pl.edu.agh.calculationp2p.state.future.Future;
import pl.edu.agh.calculationp2p.state.future.Observation;

public class ObserveTaskRequest implements MethodRequest {
    Future<Void> flag;
    Integer taskID;


    public ObserveTaskRequest(Integer taskId, Future<Void> flag) {
        this.flag = flag;
        this.taskID = taskID;
    }

    @Override
    public void call(Servant servant) {
        servant.getTaskPublisher().subscribe(taskID, flag, servant.getNodeId(), servant.getProgress());
    }

}