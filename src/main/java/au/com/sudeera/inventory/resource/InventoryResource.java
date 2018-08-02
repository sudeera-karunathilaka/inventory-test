package au.com.sudeera.inventory.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.sudeera.inventory.config.ApplicationProperties;
import au.com.sudeera.inventory.config.ApplicationProperties.Credentials;
import au.com.sudeera.inventory.factory.ConverterFactory;
import au.com.sudeera.inventory.model.Item;
import au.com.sudeera.inventory.service.InventoryService;
import au.com.sudeera.inventory.util.MessageUtil;
import au.com.sudeera.inventory.wrapper.ItemHistoryWrapper;
import au.com.sudeera.inventory.wrapper.ItemWrapper;

/**
 * The Class InventoryResource. The rest controller responsible for items.
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryResource {

	/** The converter factory. */
	private ConverterFactory converterFactory;

	/** The inventory service. */
	private InventoryService inventoryService;
	
	/** The application properties. */
	private ApplicationProperties applicationProperties;

	private MessageUtil messageUtil;
	
	/**
	 * Sets the inventory service.
	 *
	 * @param inventoryService the new inventory service
	 */
	@Autowired
	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	/**
	 * Sets the converter factory.
	 *
	 * @param converterFactory the new converter factory
	 */
	@Autowired
	public void setConverterFactory(ConverterFactory converterFactory) {
		this.converterFactory = converterFactory;
	}
	
	/**
	 * Sets the application properties.
	 *
	 * @param applicationProperties the new application properties
	 */
	@Autowired
	public void setApplicationProperties(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
	
	@Autowired
	public void setMessageUtil(MessageUtil messageUtil) {
		this.messageUtil = messageUtil;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ItemWrapper>> getItems() {
		//Dummy - Reading properties from application.yml file
		Credentials credentials = applicationProperties.getCredentials();
		List<String> defaultRecipients = applicationProperties.getDefaultRecipients();

		//Dummy - Reading messages from messages.properites file
        String defaultTitle = messageUtil.get("greeting");
	    
		List<Item> items = inventoryService.getItems();
		List<ItemWrapper> list = items.stream().map(item -> converterFactory.convertToItemWrapper(item))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ItemWrapper>>(list, HttpStatus.OK);
	}

	/**
	 * Gets the item stock record.
	 *
	 * @param code the code
	 * @return the item stock record
	 */
	@GetMapping(value = "/items/{code}/stockrecords", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ItemHistoryWrapper> getItemStockRecord(
			@NotNull @PathVariable(value = "code", required = true) String code) {
		Item item = inventoryService.getItemByCode(code);
		ItemHistoryWrapper itemHistoryWrapper = converterFactory.convertToItemHistoryWrapper(item);
		return new ResponseEntity<ItemHistoryWrapper>(itemHistoryWrapper, HttpStatus.OK);
	}

	/**
	 * Creates the item.
	 *
	 * @param itemWrapper the item wrapper
	 * @return the response entity
	 */
	@PostMapping(value = "/items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ItemWrapper> createItem(@Valid @RequestBody ItemWrapper itemWrapper) {
		Item item = converterFactory.convertToItem(itemWrapper);

		return new ResponseEntity<ItemWrapper>(converterFactory.convertToItemWrapper(inventoryService.createItem(item)),
				HttpStatus.CREATED);
	}

	/**
	 * Update item.
	 *
	 * @param itemWrapper the item wrapper
	 * @return the response entity
	 */
	@PutMapping(value = "/items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ItemWrapper> updateItem(@Valid @RequestBody ItemWrapper itemWrapper) {
		Item item = converterFactory.convertToItem(itemWrapper);

		return new ResponseEntity<ItemWrapper>(converterFactory.convertToItemWrapper(inventoryService.updateItem(item)),
				HttpStatus.OK);
	}

}
