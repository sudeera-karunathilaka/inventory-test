package au.com.sudeera.inventory.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The Class MessageUtil. Responsible for reading the correct message file
 * depending on the "Accept-Language" header.
 */
@Component
public class MessageUtil {

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/** The accessor. */
	private MessageSourceAccessor accessor;

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
	}

	/**
	 * Gets the.
	 *
	 * @param code the code
	 * @return the string
	 */
	public String get(String code) {
		return get(code, getClientLocale());
	}

	/**
	 * Gets the.
	 *
	 * @param code the code
	 * @param locale the locale
	 * @return the string
	 */
	public String get(String code, Locale locale) {
		return accessor.getMessage(code, locale);
	}

	/**
	 * Gets the client locale.
	 *
	 * @return the client locale
	 */
	private Locale getClientLocale() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest().getLocale();
		} catch (Exception e) {
			return Locale.ENGLISH;
		}
	}
}
