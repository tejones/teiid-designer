package org.teiid.datatools.connectivity.ui;

import java.sql.Connection;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.services.ExecutionService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.debug.core.ILaunchConfiguration;

public class TeiidExcecutionService extends ExecutionService {
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.services.ExecutionService#createCallableSQLResultRunnable(java.sql.Connection, org.eclipse.debug.core.ILaunchConfiguration, boolean, org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker, org.eclipse.datatools.sqltools.core.DatabaseIdentifier)
	 */
	public Runnable createCallableSQLResultRunnable(Connection con,
			ILaunchConfiguration configuration, boolean closeCon,
			IConnectionTracker tracker, DatabaseIdentifier databaseIdentifier) {
		try {
			return new TeiidCallableSQLResultRunnable(con, configuration, closeCon,
					tracker, databaseIdentifier);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Runnable createSimpleSQLResultRunnable(Connection con, String sql,
			boolean closeCon, IConnectionTracker tracker,
			IProgressMonitor parentMonitor,
			DatabaseIdentifier databaseIdentifier,
			ILaunchConfiguration configuration, HashMap addInfo) {
		return new TeiidAdHocScriptRunnable(con, sql, closeCon, tracker,
				parentMonitor, databaseIdentifier, configuration, addInfo);
	}
	
	

}
