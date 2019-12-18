import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWMonitorAN {
    int lectores;
    boolean escribiendo;
    private ReentrantLock lock = new ReentrantLock();
    Condition c;
}