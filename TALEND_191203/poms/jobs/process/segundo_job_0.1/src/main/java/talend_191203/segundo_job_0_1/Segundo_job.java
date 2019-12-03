
package talend_191203.segundo_job_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Segundo_job Purpose: Segundo_job<br>
 * Description: Segundo_job <br>
 * 
 * @author andres.torres.mendoza@devoteam.com
 * @version 7.2.1.20190909_1200_patch
 * @status
 */
public class Segundo_job implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Segundo_job.log");
	}
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Segundo_job.class);

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Segundo_job";
	private final String projectName = "TALEND_191203";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName, "_s40FYBW8EeqTnYL8FT7g6A", "0.1");
	org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Segundo_job.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Segundo_job.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_TALEND_191203_Segundo_job = new byte[0];
		static byte[] commonByteArray_TALEND_191203_Segundo_job = new byte[0];

		public Integer InvoiceId;

		public Integer getInvoiceId() {
			return this.InvoiceId;
		}

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public String InvoiceDate;

		public String getInvoiceDate() {
			return this.InvoiceDate;
		}

		public String BillingAddress;

		public String getBillingAddress() {
			return this.BillingAddress;
		}

		public String BillingCity;

		public String getBillingCity() {
			return this.BillingCity;
		}

		public String BillingState;

		public String getBillingState() {
			return this.BillingState;
		}

		public String BillingCountry;

		public String getBillingCountry() {
			return this.BillingCountry;
		}

		public String BillingPostalCode;

		public String getBillingPostalCode() {
			return this.BillingPostalCode;
		}

		public Float Total;

		public Float getTotal() {
			return this.Total;
		}

		public String continent;

		public String getContinent() {
			return this.continent;
		}

		public Integer CustomerId_1;

		public Integer getCustomerId_1() {
			return this.CustomerId_1;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Company;

		public String getCompany() {
			return this.Company;
		}

		public String Address;

		public String getAddress() {
			return this.Address;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_191203_Segundo_job.length) {
					if (length < 1024 && commonByteArray_TALEND_191203_Segundo_job.length == 0) {
						commonByteArray_TALEND_191203_Segundo_job = new byte[1024];
					} else {
						commonByteArray_TALEND_191203_Segundo_job = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_191203_Segundo_job, 0, length);
				strReturn = new String(commonByteArray_TALEND_191203_Segundo_job, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_191203_Segundo_job) {

				try {

					int length = 0;

					this.InvoiceId = readInteger(dis);

					this.CustomerId = readInteger(dis);

					this.InvoiceDate = readString(dis);

					this.BillingAddress = readString(dis);

					this.BillingCity = readString(dis);

					this.BillingState = readString(dis);

					this.BillingCountry = readString(dis);

					this.BillingPostalCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Total = null;
					} else {
						this.Total = dis.readFloat();
					}

					this.continent = readString(dis);

					this.CustomerId_1 = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Company = readString(dis);

					this.Address = readString(dis);

					this.Email = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.InvoiceId, dos);

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.InvoiceDate, dos);

				// String

				writeString(this.BillingAddress, dos);

				// String

				writeString(this.BillingCity, dos);

				// String

				writeString(this.BillingState, dos);

				// String

				writeString(this.BillingCountry, dos);

				// String

				writeString(this.BillingPostalCode, dos);

				// Float

				if (this.Total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total);
				}

				// String

				writeString(this.continent, dos);

				// Integer

				writeInteger(this.CustomerId_1, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Company, dos);

				// String

				writeString(this.Address, dos);

				// String

				writeString(this.Email, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("InvoiceId=" + String.valueOf(InvoiceId));
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",InvoiceDate=" + InvoiceDate);
			sb.append(",BillingAddress=" + BillingAddress);
			sb.append(",BillingCity=" + BillingCity);
			sb.append(",BillingState=" + BillingState);
			sb.append(",BillingCountry=" + BillingCountry);
			sb.append(",BillingPostalCode=" + BillingPostalCode);
			sb.append(",Total=" + String.valueOf(Total));
			sb.append(",continent=" + continent);
			sb.append(",CustomerId_1=" + String.valueOf(CustomerId_1));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Company=" + Company);
			sb.append(",Address=" + Address);
			sb.append(",Email=" + Email);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (InvoiceId == null) {
				sb.append("<null>");
			} else {
				sb.append(InvoiceId);
			}

			sb.append("|");

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (InvoiceDate == null) {
				sb.append("<null>");
			} else {
				sb.append(InvoiceDate);
			}

			sb.append("|");

			if (BillingAddress == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingAddress);
			}

			sb.append("|");

			if (BillingCity == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingCity);
			}

			sb.append("|");

			if (BillingState == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingState);
			}

			sb.append("|");

			if (BillingCountry == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingCountry);
			}

			sb.append("|");

			if (BillingPostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingPostalCode);
			}

			sb.append("|");

			if (Total == null) {
				sb.append("<null>");
			} else {
				sb.append(Total);
			}

			sb.append("|");

			if (continent == null) {
				sb.append("<null>");
			} else {
				sb.append(continent);
			}

			sb.append("|");

			if (CustomerId_1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId_1);
			}

			sb.append("|");

			if (FirstName == null) {
				sb.append("<null>");
			} else {
				sb.append(FirstName);
			}

			sb.append("|");

			if (LastName == null) {
				sb.append("<null>");
			} else {
				sb.append(LastName);
			}

			sb.append("|");

			if (Company == null) {
				sb.append("<null>");
			} else {
				sb.append(Company);
			}

			sb.append("|");

			if (Address == null) {
				sb.append("<null>");
			} else {
				sb.append(Address);
			}

			sb.append("|");

			if (Email == null) {
				sb.append("<null>");
			} else {
				sb.append(Email);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TALEND_191203_Segundo_job = new byte[0];
		static byte[] commonByteArray_TALEND_191203_Segundo_job = new byte[0];

		public Integer InvoiceId;

		public Integer getInvoiceId() {
			return this.InvoiceId;
		}

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public String InvoiceDate;

		public String getInvoiceDate() {
			return this.InvoiceDate;
		}

		public String BillingAddress;

		public String getBillingAddress() {
			return this.BillingAddress;
		}

		public String BillingCity;

		public String getBillingCity() {
			return this.BillingCity;
		}

		public String BillingState;

		public String getBillingState() {
			return this.BillingState;
		}

		public String BillingCountry;

		public String getBillingCountry() {
			return this.BillingCountry;
		}

		public String BillingPostalCode;

		public String getBillingPostalCode() {
			return this.BillingPostalCode;
		}

		public Float Total;

		public Float getTotal() {
			return this.Total;
		}

		public String continent;

		public String getContinent() {
			return this.continent;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_191203_Segundo_job.length) {
					if (length < 1024 && commonByteArray_TALEND_191203_Segundo_job.length == 0) {
						commonByteArray_TALEND_191203_Segundo_job = new byte[1024];
					} else {
						commonByteArray_TALEND_191203_Segundo_job = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_191203_Segundo_job, 0, length);
				strReturn = new String(commonByteArray_TALEND_191203_Segundo_job, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_191203_Segundo_job) {

				try {

					int length = 0;

					this.InvoiceId = readInteger(dis);

					this.CustomerId = readInteger(dis);

					this.InvoiceDate = readString(dis);

					this.BillingAddress = readString(dis);

					this.BillingCity = readString(dis);

					this.BillingState = readString(dis);

					this.BillingCountry = readString(dis);

					this.BillingPostalCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Total = null;
					} else {
						this.Total = dis.readFloat();
					}

					this.continent = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.InvoiceId, dos);

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.InvoiceDate, dos);

				// String

				writeString(this.BillingAddress, dos);

				// String

				writeString(this.BillingCity, dos);

				// String

				writeString(this.BillingState, dos);

				// String

				writeString(this.BillingCountry, dos);

				// String

				writeString(this.BillingPostalCode, dos);

				// Float

				if (this.Total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total);
				}

				// String

				writeString(this.continent, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("InvoiceId=" + String.valueOf(InvoiceId));
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",InvoiceDate=" + InvoiceDate);
			sb.append(",BillingAddress=" + BillingAddress);
			sb.append(",BillingCity=" + BillingCity);
			sb.append(",BillingState=" + BillingState);
			sb.append(",BillingCountry=" + BillingCountry);
			sb.append(",BillingPostalCode=" + BillingPostalCode);
			sb.append(",Total=" + String.valueOf(Total));
			sb.append(",continent=" + continent);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (InvoiceId == null) {
				sb.append("<null>");
			} else {
				sb.append(InvoiceId);
			}

			sb.append("|");

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (InvoiceDate == null) {
				sb.append("<null>");
			} else {
				sb.append(InvoiceDate);
			}

			sb.append("|");

			if (BillingAddress == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingAddress);
			}

			sb.append("|");

			if (BillingCity == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingCity);
			}

			sb.append("|");

			if (BillingState == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingState);
			}

			sb.append("|");

			if (BillingCountry == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingCountry);
			}

			sb.append("|");

			if (BillingPostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingPostalCode);
			}

			sb.append("|");

			if (Total == null) {
				sb.append("<null>");
			} else {
				sb.append(Total);
			}

			sb.append("|");

			if (continent == null) {
				sb.append("<null>");
			} else {
				sb.append(continent);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_1Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_1Struct> {
		final static byte[] commonByteArrayLock_TALEND_191203_Segundo_job = new byte[0];
		static byte[] commonByteArray_TALEND_191203_Segundo_job = new byte[0];

		public Integer InvoiceId;

		public Integer getInvoiceId() {
			return this.InvoiceId;
		}

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public String InvoiceDate;

		public String getInvoiceDate() {
			return this.InvoiceDate;
		}

		public String BillingAddress;

		public String getBillingAddress() {
			return this.BillingAddress;
		}

		public String BillingCity;

		public String getBillingCity() {
			return this.BillingCity;
		}

		public String BillingState;

		public String getBillingState() {
			return this.BillingState;
		}

		public String BillingCountry;

		public String getBillingCountry() {
			return this.BillingCountry;
		}

		public String BillingPostalCode;

		public String getBillingPostalCode() {
			return this.BillingPostalCode;
		}

		public Float Total;

		public Float getTotal() {
			return this.Total;
		}

		public String continent;

		public String getContinent() {
			return this.continent;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_191203_Segundo_job.length) {
					if (length < 1024 && commonByteArray_TALEND_191203_Segundo_job.length == 0) {
						commonByteArray_TALEND_191203_Segundo_job = new byte[1024];
					} else {
						commonByteArray_TALEND_191203_Segundo_job = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_191203_Segundo_job, 0, length);
				strReturn = new String(commonByteArray_TALEND_191203_Segundo_job, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_191203_Segundo_job) {

				try {

					int length = 0;

					this.InvoiceId = readInteger(dis);

					this.CustomerId = readInteger(dis);

					this.InvoiceDate = readString(dis);

					this.BillingAddress = readString(dis);

					this.BillingCity = readString(dis);

					this.BillingState = readString(dis);

					this.BillingCountry = readString(dis);

					this.BillingPostalCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Total = null;
					} else {
						this.Total = dis.readFloat();
					}

					this.continent = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.InvoiceId, dos);

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.InvoiceDate, dos);

				// String

				writeString(this.BillingAddress, dos);

				// String

				writeString(this.BillingCity, dos);

				// String

				writeString(this.BillingState, dos);

				// String

				writeString(this.BillingCountry, dos);

				// String

				writeString(this.BillingPostalCode, dos);

				// Float

				if (this.Total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total);
				}

				// String

				writeString(this.continent, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("InvoiceId=" + String.valueOf(InvoiceId));
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",InvoiceDate=" + InvoiceDate);
			sb.append(",BillingAddress=" + BillingAddress);
			sb.append(",BillingCity=" + BillingCity);
			sb.append(",BillingState=" + BillingState);
			sb.append(",BillingCountry=" + BillingCountry);
			sb.append(",BillingPostalCode=" + BillingPostalCode);
			sb.append(",Total=" + String.valueOf(Total));
			sb.append(",continent=" + continent);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (InvoiceId == null) {
				sb.append("<null>");
			} else {
				sb.append(InvoiceId);
			}

			sb.append("|");

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (InvoiceDate == null) {
				sb.append("<null>");
			} else {
				sb.append(InvoiceDate);
			}

			sb.append("|");

			if (BillingAddress == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingAddress);
			}

			sb.append("|");

			if (BillingCity == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingCity);
			}

			sb.append("|");

			if (BillingState == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingState);
			}

			sb.append("|");

			if (BillingCountry == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingCountry);
			}

			sb.append("|");

			if (BillingPostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(BillingPostalCode);
			}

			sb.append("|");

			if (Total == null) {
				sb.append("<null>");
			} else {
				sb.append(Total);
			}

			sb.append("|");

			if (continent == null) {
				sb.append("<null>");
			} else {
				sb.append(continent);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_2Process(globalMap);

				row1Struct row1 = new row1Struct();
				out1Struct out1 = new out1Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat || enableLogStash) {
					if (resourceMap.get("inIterateVComp") == null) {

						if (execStat) {
							runStat.updateStatOnConnection("out1" + iterateId, 0, 0);
						}

						if (enableLogStash) {
							runStat.logStatOnConnection("out1" + iterateId, 0, 0);
						}

					}
				}

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("FIELDSEPARATOR" + " = " + "\"|\"");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_HEADER" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("USE_FIXED_LENGTH" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_1 - " + (log4jParamters_tLogRow_1));
						}
					}
					new BytesLimit65535_tLogRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addComponentMessage("tLogRow_1", "tLogRow");
					talendJobLogProcess(globalMap);
				}

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat || enableLogStash) {
					if (resourceMap.get("inIterateVComp") == null) {

						if (execStat) {
							runStat.updateStatOnConnection("row1" + iterateId, 0, 0);
						}

						if (enableLogStash) {
							runStat.logStatOnConnection("row1" + iterateId, 0, 0);
						}

					}
				}

				int tos_count_tMap_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_1 = new StringBuilder();
							log4jParamters_tMap_1.append("Parameters:");
							log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_1 - " + (log4jParamters_tMap_1));
						}
					}
					new BytesLimit65535_tMap_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addComponentMessage("tMap_1", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row1_tMap_1 = 0;

				int count_row2_tMap_1 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out1_tMap_1 = 0;

				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_1 = new StringBuilder();
							log4jParamters_tFileInputDelimited_1.append("Parameters:");
							log4jParamters_tFileInputDelimited_1
									.append("FILENAME" + " = " + "\"C:/Talend/Invoice.csv\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("InvoiceId") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("CustomerId") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("InvoiceDate")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("BillingAddress") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("BillingCity") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("BillingState") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("BillingCountry") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("BillingPostalCode") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Total") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("continent") + "}]");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENCODING" + " = " + "\"UTF-8\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_1 - " + (log4jParamters_tFileInputDelimited_1));
						}
					}
					new BytesLimit65535_tFileInputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addComponentMessage("tFileInputDelimited_1", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Talend/Invoice.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Talend/Invoice.csv", "UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_1,
								-1, false);
					} catch (java.lang.Exception e) {

						log.error("tFileInputDelimited_1 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_1 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.InvoiceId = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"InvoiceId", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.InvoiceId = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.CustomerId = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CustomerId", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.CustomerId = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row1.InvoiceDate = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.BillingAddress = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.BillingCity = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.BillingState = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.BillingCountry = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row1.BillingPostalCode = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 8;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Total = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Total", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Total = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row1.continent = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_1 = true;

							log.error("tFileInputDelimited_1 - " + e.getMessage());

							System.err.println(e.getMessage());
							row1 = null;

						}

						log.debug("tFileInputDelimited_1 - Retrieving the record "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							// row1
							// row1

							if (execStat) {
								runStat.updateStatOnConnection("row1" + iterateId, 1, 1);
							}

							if (enableLogStash) {
								runStat.logStatOnConnection("row1" + iterateId, 1, 1);
							}

							if (log.isTraceEnabled()) {
								log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row2"
							///////////////////////////////////////////////

							boolean forceLooprow2 = false;

							row2Struct row2ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row2HashKey.CustomerId = row1.CustomerId;

								row2HashKey.hashCodeDirty = true;

								tHash_Lookup_row2.lookup(row2HashKey);

								if (!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_1 = true;

									forceLooprow2 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							else { // G 20 - G 21
								forceLooprow2 = true;
							} // G 21

							row2Struct row2 = null;

							while ((tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) || forceLooprow2) { // G_TM_M_043

								// CALL close loop of lookup 'row2'

								row2Struct fromLookup_row2 = null;
								row2 = row2Default;

								if (!forceLooprow2) { // G 46

									fromLookup_row2 = tHash_Lookup_row2.next();

									if (fromLookup_row2 != null) {
										row2 = fromLookup_row2;
									}

								} // G 46

								forceLooprow2 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									out1 = null;

									if (!rejectedInnerJoin_tMap_1) {

// # Output table : 'out1'
										count_out1_tMap_1++;

										out1_tmp.InvoiceId = row1.InvoiceId;
										out1_tmp.CustomerId = row1.CustomerId;
										out1_tmp.InvoiceDate = row1.InvoiceDate;
										out1_tmp.BillingAddress = row1.BillingAddress;
										out1_tmp.BillingCity = row1.BillingCity;
										out1_tmp.BillingState = row1.BillingState;
										out1_tmp.BillingCountry = row1.BillingCountry;
										out1_tmp.BillingPostalCode = row1.BillingPostalCode;
										out1_tmp.Total = row1.Total;
										out1_tmp.continent = row1.continent;
										out1_tmp.CustomerId_1 = row2.CustomerId;
										out1_tmp.FirstName = row2.FirstName;
										out1_tmp.LastName = row2.LastName;
										out1_tmp.Company = row2.Company;
										out1_tmp.Address = row2.Address;
										out1_tmp.Email = row2.Email;
										out1 = out1_tmp;
										log.debug("tMap_1 - Outputting the record " + count_out1_tMap_1
												+ " of the output table 'out1'.");

									} // closing inner join bracket (2)
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "out1"
								if (out1 != null) {

									/**
									 * [tLogRow_1 main ] start
									 */

									currentComponent = "tLogRow_1";

									// out1
									// out1

									if (execStat) {
										runStat.updateStatOnConnection("out1" + iterateId, 1, 1);
									}

									if (enableLogStash) {
										runStat.logStatOnConnection("out1" + iterateId, 1, 1);
									}

									if (log.isTraceEnabled()) {
										log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
									}

///////////////////////		

									strBuffer_tLogRow_1 = new StringBuilder();

									if (out1.InvoiceId != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.InvoiceId));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.CustomerId != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.CustomerId));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.InvoiceDate != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.InvoiceDate));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.BillingAddress != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.BillingAddress));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.BillingCity != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.BillingCity));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.BillingState != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.BillingState));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.BillingCountry != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.BillingCountry));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.BillingPostalCode != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.BillingPostalCode));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.Total != null) { //

										strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(out1.Total));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.continent != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.continent));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.CustomerId_1 != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.CustomerId_1));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.FirstName != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.FirstName));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.LastName != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.LastName));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.Company != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.Company));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.Address != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.Address));

									} //

									strBuffer_tLogRow_1.append("|");

									if (out1.Email != null) { //

										strBuffer_tLogRow_1.append(String.valueOf(out1.Email));

									} //

									if (globalMap.get("tLogRow_CONSOLE") != null) {
										consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
									} else {
										consoleOut_tLogRow_1 = new java.io.PrintStream(
												new java.io.BufferedOutputStream(System.out));
										globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
									}
									log.info("tLogRow_1 - Content of row " + (nb_line_tLogRow_1 + 1) + ": "
											+ strBuffer_tLogRow_1.toString());
									consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
									consoleOut_tLogRow_1.flush();
									nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

									tos_count_tLogRow_1++;

									/**
									 * [tLogRow_1 main ] stop
									 */

									/**
									 * [tLogRow_1 process_data_begin ] start
									 */

									currentComponent = "tLogRow_1";

									/**
									 * [tLogRow_1 process_data_begin ] stop
									 */

									/**
									 * [tLogRow_1 process_data_end ] start
									 */

									currentComponent = "tLogRow_1";

									/**
									 * [tLogRow_1 process_data_end ] stop
									 */

								} // End of branch "out1"

							} // close loop of lookup 'row2' // G_TM_M_043

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Talend/Invoice.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

						log.info("tFileInputDelimited_1 - Retrieved records count: "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");

				if (execStat) {
					if (resourceMap.get("inIterateVComp") == null || !((Boolean) resourceMap.get("inIterateVComp"))) {
						runStat.updateStatOnConnection("row1" + iterateId, 2, 0);
					}
				}

				if (enableLogStash) {
					if (resourceMap.get("inIterateVComp") == null || !((Boolean) resourceMap.get("inIterateVComp"))) {

						RunStat.StatBean talend_statebean = runStat.logStatOnConnection("row1" + iterateId, 2, 0);

						talendJobLog.addConnectionMessage("tFileInputDelimited_1", "tFileInputDelimited", false,
								"output", "row1", talend_statebean.getNbLine(), talend_statebean.getStartTime(),
								talend_statebean.getEndTime());

						talendJobLog.addConnectionMessage("tMap_1", "tMap", true, "input", "row1",
								talend_statebean.getNbLine(), talend_statebean.getStartTime(),
								talend_statebean.getEndTime());
						talendJobLogProcess(globalMap);

					}
				}

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Done."));

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (execStat) {
					if (resourceMap.get("inIterateVComp") == null || !((Boolean) resourceMap.get("inIterateVComp"))) {
						runStat.updateStatOnConnection("out1" + iterateId, 2, 0);
					}
				}

				if (enableLogStash) {
					if (resourceMap.get("inIterateVComp") == null || !((Boolean) resourceMap.get("inIterateVComp"))) {

						RunStat.StatBean talend_statebean = runStat.logStatOnConnection("out1" + iterateId, 2, 0);

						talendJobLog.addConnectionMessage("tMap_1", "tMap", false, "output", "out1",
								talend_statebean.getNbLine(), talend_statebean.getStartTime(),
								talend_statebean.getEndTime());

						talendJobLog.addConnectionMessage("tLogRow_1", "tLogRow", true, "input", "out1",
								talend_statebean.getNbLine(), talend_statebean.getStartTime(),
								talend_statebean.getEndTime());
						talendJobLogProcess(globalMap);

					}
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row2");

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_TALEND_191203_Segundo_job = new byte[0];
		static byte[] commonByteArray_TALEND_191203_Segundo_job = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Company;

		public String getCompany() {
			return this.Company;
		}

		public String Address;

		public String getAddress() {
			return this.Address;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.CustomerId == null) ? 0 : this.CustomerId.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.CustomerId == null) {
				if (other.CustomerId != null)
					return false;

			} else if (!this.CustomerId.equals(other.CustomerId))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.CustomerId = this.CustomerId;
			other.FirstName = this.FirstName;
			other.LastName = this.LastName;
			other.Company = this.Company;
			other.Address = this.Address;
			other.Email = this.Email;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.CustomerId = this.CustomerId;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_191203_Segundo_job) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.FirstName = readString(dis, ois);

				this.LastName = readString(dis, ois);

				this.Company = readString(dis, ois);

				this.Address = readString(dis, ois);

				this.Email = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.FirstName, dos, oos);

				writeString(this.LastName, dos, oos);

				writeString(this.Company, dos, oos);

				writeString(this.Address, dos, oos);

				writeString(this.Email, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + String.valueOf(CustomerId));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Company=" + Company);
			sb.append(",Address=" + Address);
			sb.append(",Email=" + Email);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (FirstName == null) {
				sb.append("<null>");
			} else {
				sb.append(FirstName);
			}

			sb.append("|");

			if (LastName == null) {
				sb.append("<null>");
			} else {
				sb.append(LastName);
			}

			sb.append("|");

			if (Company == null) {
				sb.append("<null>");
			} else {
				sb.append(Company);
			}

			sb.append("|");

			if (Address == null) {
				sb.append("<null>");
			} else {
				sb.append(Address);
			}

			sb.append("|");

			if (Email == null) {
				sb.append("<null>");
			} else {
				sb.append(Email);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.CustomerId, other.CustomerId);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row2", false);
				start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row2";

				if (execStat || enableLogStash) {
					if (resourceMap.get("inIterateVComp") == null) {

						if (execStat) {
							runStat.updateStatOnConnection("row2" + iterateId, 0, 0);
						}

						if (enableLogStash) {
							runStat.logStatOnConnection("row2" + iterateId, 0, 0);
						}

					}
				}

				int tos_count_tAdvancedHash_row2 = 0;

				if (enableLogStash) {
					talendJobLog.addComponentMessage("tAdvancedHash_row2", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row2
				// source node:tFileInputDelimited_2 - inputs:(after_tFileInputDelimited_1)
				// outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2)
				// outputs:()
				// linked node: tMap_1 - inputs:(row1,row2) outputs:(out1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_2 = new StringBuilder();
							log4jParamters_tFileInputDelimited_2.append("Parameters:");
							log4jParamters_tFileInputDelimited_2
									.append("FILENAME" + " = " + "\"C:/Talend/CustomerCorto.csv\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CustomerId") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("FirstName") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("LastName")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Company") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Address") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Email") + "}]");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ENCODING" + " = " + "\"UTF-8\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_2 - " + (log4jParamters_tFileInputDelimited_2));
						}
					}
					new BytesLimit65535_tFileInputDelimited_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addComponentMessage("tFileInputDelimited_2", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/Talend/CustomerCorto.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Talend/CustomerCorto.csv", "UTF-8", ";", "\n", false, 1, 0,
								limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {

						log.error("tFileInputDelimited_2 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_2 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row2 = null;

						row2 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.CustomerId = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CustomerId", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.CustomerId = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 1;

							row2.FirstName = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 2;

							row2.LastName = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 3;

							row2.Company = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 4;

							row2.Address = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 5;

							row2.Email = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							whetherReject_tFileInputDelimited_2 = true;

							log.error("tFileInputDelimited_2 - " + e.getMessage());

							System.err.println(e.getMessage());
							row2 = null;

						}

						log.debug("tFileInputDelimited_2 - Retrieving the record "
								+ fid_tFileInputDelimited_2.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tAdvancedHash_row2 main ] start
							 */

							currentComponent = "tAdvancedHash_row2";

							// row2
							// row2

							if (execStat) {
								runStat.updateStatOnConnection("row2" + iterateId, 1, 1);
							}

							if (enableLogStash) {
								runStat.logStatOnConnection("row2" + iterateId, 1, 1);
							}

							if (log.isTraceEnabled()) {
								log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
							}

							row2Struct row2_HashRow = new row2Struct();

							row2_HashRow.CustomerId = row2.CustomerId;

							row2_HashRow.FirstName = row2.FirstName;

							row2_HashRow.LastName = row2.LastName;

							row2_HashRow.Company = row2.Company;

							row2_HashRow.Address = row2.Address;

							row2_HashRow.Email = row2.Email;

							tHash_Lookup_row2.put(row2_HashRow);

							tos_count_tAdvancedHash_row2++;

							/**
							 * [tAdvancedHash_row2 main ] stop
							 */

							/**
							 * [tAdvancedHash_row2 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row2";

							/**
							 * [tAdvancedHash_row2 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row2 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row2";

							/**
							 * [tAdvancedHash_row2 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/Talend/CustomerCorto.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

						log.info("tFileInputDelimited_2 - Retrieved records count: "
								+ fid_tFileInputDelimited_2.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_2 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row2 end ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				tHash_Lookup_row2.endPut();

				if (execStat) {
					if (resourceMap.get("inIterateVComp") == null || !((Boolean) resourceMap.get("inIterateVComp"))) {
						runStat.updateStatOnConnection("row2" + iterateId, 2, 0);
					}
				}

				if (enableLogStash) {
					if (resourceMap.get("inIterateVComp") == null || !((Boolean) resourceMap.get("inIterateVComp"))) {

						RunStat.StatBean talend_statebean = runStat.logStatOnConnection("row2" + iterateId, 2, 0);

						talendJobLog.addConnectionMessage("tFileInputDelimited_2", "tFileInputDelimited", false,
								"output", "row2", talend_statebean.getNbLine(), talend_statebean.getStartTime(),
								talend_statebean.getEndTime());

						talendJobLog.addConnectionMessage("tAdvancedHash_row2", "tAdvancedHash", true, "input", "row2",
								talend_statebean.getNbLine(), talend_statebean.getStartTime(),
								talend_statebean.getEndTime());
						talendJobLogProcess(globalMap);

					}
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				/**
				 * [tAdvancedHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				ok_Hash.put("talendJobLog", false);
				start_Hash.put("talendJobLog", System.currentTimeMillis());

				currentComponent = "talendJobLog";

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.logging.audit.Context log_context_talendJobLog = null;
					if (jcm.component_name == null) {// job level log
						if (jcm.status == null) {// job start
							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.timestamp(jcm.moment).build();
							auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
						} else {// job end
							long timeMS = jcm.end_time - jcm.start_time;
							String duration = String.format(java.util.Locale.US, "%1$.2fs", (timeMS * 1.0) / 1000);

							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.timestamp(jcm.moment).duration(duration).status(jcm.status).build();
							auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
						}
					} else if (jcm.current_connector == null) {// component log
						log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create().jobName(jcm.job_name)
								.jobId(jcm.job_id).jobVersion(jcm.job_version).connectorType(jcm.component_name)
								.connectorId(jcm.component_id).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else {// component connector meter log
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.format(java.util.Locale.US, "%1$.2fs", (timeMS * 1.0) / 1000);

						if (jcm.current_connector_as_input) {// log current component input line
							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.connectorType(jcm.component_name).connectorId(jcm.component_id)
									.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
									.rows(jcm.total_row_number).duration(duration).build();
							auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
						} else {// log current component output/reject line
							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.connectorType(jcm.component_name).connectorId(jcm.component_id)
									.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
									.rows(jcm.total_row_number).duration(duration).build();
							auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
						}
					}
				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				currentComponent = "talendJobLog";

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				currentComponent = "talendJobLog";

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Segundo_job Segundo_jobClass = new Segundo_job();

		int exitCode = Segundo_jobClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Segundo_job' - Done.");
		}

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		if (!"".equals(log4jLevel)) {
			if ("trace".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				log.setLevel(org.apache.log4j.Level.OFF);
			}
			org.apache.log4j.Logger.getRootLogger().setLevel(log.getLevel());
		}
		log.info("TalendJob: 'Segundo_job' - Start.");

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Segundo_job.class.getClassLoader()
					.getResourceAsStream("talend_191203/segundo_job_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Segundo_job.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				// defaultProps is in order to keep the original context value
				if (context != null && context.isEmpty()) {
					defaultProps.load(inContext);
					context = new ContextProperties(defaultProps);
				}

				inContext.close();
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Segundo_job");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--monitoring=")) {// for trunjob call
			enableLogStash = "true".equalsIgnoreCase(arg.substring(13));
		}

		if (!enableLogStash) {
			enableLogStash = "true".equalsIgnoreCase(System.getProperty("monitoring"));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 122380 characters generated by Talend Cloud Big Data on the 3 de diciembre de
 * 2019 12:16:23 CET
 ************************************************************************************************/