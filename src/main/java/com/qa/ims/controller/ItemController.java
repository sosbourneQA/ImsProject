package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemController.class);

	private CrudServices<Item> itemService;

	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Item> readAll() {

		List<Item> items = itemService.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String name = getInput();
		LOGGER.info("Please enter price");
		double price = Double.parseDouble(getInput());
		Item item = itemService.create(new Item(name, price));
		LOGGER.info("Item created");
		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
		String answer = getInput();
		if (answer.toLowerCase().equals("return")) {
			Ims newIms = new Ims();
			newIms.imsSystem();
		}
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("please enter the id of the item you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("please enter a name");
		String name = getInput();
		LOGGER.info("please enter a price");
		double price = Double.valueOf(getInput());
		Item item = itemService.update(new Item(id, name, price));
		LOGGER.info("Item Updated");
		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
		String answer = getInput();
		if (answer.toLowerCase().equals("return")) {
			Ims newIms = new Ims();
			newIms.imsSystem();
		}
		return item;
	}

	@Override
	public void delete() {
		LOGGER.info("please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		itemService.delete(id);
		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
		String answer = getInput();
		if (answer.toLowerCase().equals("return")) {
			Ims newIms = new Ims();
			newIms.imsSystem();
		}

	}

}
