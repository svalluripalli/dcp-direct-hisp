package gov.cdc.dhcs.hehr.dcp.direct.executor;

import java.util.List;

import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * 
 * @author Sai Valluripalli
 *
 */
public class ExecutorStarter implements Runnable {
	
	//private static final Logger logger = LoggerFactory.getLogger(ExecutorStarter.class);
	
	private List<Runnable> executions;
	
	private TaskExecutor executor = new SyncTaskExecutor();

	public ExecutorStarter(List<Runnable> executions) {
		super();
		this.executions = executions;
	}

	@Override
	public void run() {
		System.out.println("Number of executions="+executions.size());
		executions.forEach(e -> {
			System.out.println("Executing "+e);
			executor.execute(e);
		});
		System.out.println("All executions completed");
	}

}
