package au.com.sudeera.inventory.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * The Class ErrorProperties. Responsible for reading properties, that start
 * with "my-prop", from Application.properties file.
 */
@Configuration
@PropertySource("classpath:Application.yml")
@ConfigurationProperties(prefix = "my-prop")
public class ApplicationProperties {

	/**
	 * The Class Credentials.
	 */
	public static class Credentials {
		
		/** The auth method. */
		private String authMethod;
		
		/** The username. */
		private String username;
		
		/** The password. */
		private String password;

		/**
		 * Gets the auth method.
		 *
		 * @return the auth method
		 */
		public String getAuthMethod() {
			return authMethod;
		}

		/**
		 * Sets the auth method.
		 *
		 * @param authMethod the new auth method
		 */
		public void setAuthMethod(String authMethod) {
			this.authMethod = authMethod;
		}

		/**
		 * Gets the username.
		 *
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * Sets the username.
		 *
		 * @param username the new username
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * Gets the password.
		 *
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * Sets the password.
		 *
		 * @param password the new password
		 */
		public void setPassword(String password) {
			this.password = password;
		}

	}

	/** The host. */
	private String host;
	
	/** The port. */
	private int port;
	
	/** The from. */
	private String from;
	
	/** The credentials. */
	private Credentials credentials;
	
	/** The default recipients. */
	private List<String> defaultRecipients;
	
	/** The additional headers. */
	private Map<String, String> additionalHeaders;

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Gets the credentials.
	 *
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * Sets the credentials.
	 *
	 * @param credentials the new credentials
	 */
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	/**
	 * Gets the default recipients.
	 *
	 * @return the default recipients
	 */
	public List<String> getDefaultRecipients() {
		return defaultRecipients;
	}

	/**
	 * Sets the default recipients.
	 *
	 * @param defaultRecipients the new default recipients
	 */
	public void setDefaultRecipients(List<String> defaultRecipients) {
		this.defaultRecipients = defaultRecipients;
	}

	/**
	 * Gets the additional headers.
	 *
	 * @return the additional headers
	 */
	public Map<String, String> getAdditionalHeaders() {
		return additionalHeaders;
	}

	/**
	 * Sets the additional headers.
	 *
	 * @param additionalHeaders the additional headers
	 */
	public void setAdditionalHeaders(Map<String, String> additionalHeaders) {
		this.additionalHeaders = additionalHeaders;
	}

}
