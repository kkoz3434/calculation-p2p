package pl.edu.agh.calculationp2p.state.UI;

import pl.edu.agh.calculationp2p.state.Scheduler;
import pl.edu.agh.calculationp2p.state.future.Future;
import pl.edu.agh.calculationp2p.state.request.MethodRequest;
import pl.edu.agh.calculationp2p.state.request.UIRequests.*;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.Optional;

public class UIUpdater {
    Future<Integer> nodeID;
    Future<Integer> calculatedNo;
    Future<Integer> progressSize;
    Future<Integer> taskObservers;
    Future<Integer> calculatedObservers;
    Future<Integer> reservedObservers;
    Scheduler scheduler;


    public UIUpdater(Scheduler scheduler) {
        this.scheduler = scheduler;
        updateAll();
    }

    public void updateAll() {
        updateNodeID();
        updateCalculatedNo();
        updateProgressSize();
        updateTaskObservers();
        updateCalculatedObservers();
        updateReservedObservers();
    }

    public void updateNodeID() {
        if (nodeID == null || nodeID.isReady()) {
            nodeID = new Future<>();
            MethodRequest request = new NodeIDRequest(nodeID);
            try {
                scheduler.enqueue(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCalculatedNo() {
        if (calculatedNo == null || calculatedNo.isReady()) {
            calculatedNo = new Future<>();
            MethodRequest request = new CalculatedNumberRequest(calculatedNo);
            try {
                scheduler.enqueue(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProgressSize() {
        if (progressSize == null || progressSize.isReady()) {
            progressSize = new Future<>();
            MethodRequest request = new ProgressSizeRequest(progressSize);
            try {
                scheduler.enqueue(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTaskObservers() {
        if (taskObservers == null || taskObservers.isReady()) {
            taskObservers = new Future<>();
            MethodRequest request = new TaskObserversRequest(taskObservers);
            try {
                scheduler.enqueue(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCalculatedObservers() {
        if (calculatedObservers == null || calculatedObservers.isReady()) {
            calculatedObservers = new Future<>();
            MethodRequest request = new CalculatedObserversRequest(calculatedObservers);
            try {
                scheduler.enqueue(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateReservedObservers() {
        if (reservedObservers == null || reservedObservers.isReady()) {
            reservedObservers = new Future<>();
            MethodRequest request = new ReservedObserversRequest(reservedObservers);
            try {
                scheduler.enqueue(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



