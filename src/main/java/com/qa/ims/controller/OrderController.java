package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
//		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
//		String answer = getInput();
//		if (answer.toLowerCase().equals("return")) {
//			Ims newIms = new Ims();
//			newIms.imsSystem();
//		}
		return orders;
	}

	@Override
	public Order create() {
		Long customer_id = null;
		Double total = 0.0;

		LOGGER.info("please enter the customer id");
		customer_id = Long.valueOf(getInput());

		orderService.create(new Order(customer_id, total));
		LOGGER.info("order created");
		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
		String answer = getInput();
		if (answer.toLowerCase().equals("return")) {
			Ims newIms = new Ims();
			newIms.imsSystem();
		}
		return null;

	}

	@Override
	public Order update() {

		Long customer_id = null;
		Long order_id = null;
		double total = 0;

		LOGGER.info("please enter the id of the order you would like to update");
		order_id = Long.valueOf(getInput());

		LOGGER.info("please enter the customer id of the order you want to update");
		customer_id = Long.valueOf(getInput());

		LOGGER.info("please enter the new cost of the order you would like to update");
		total = Double.valueOf(getInput());

		orderService.update(new Order(order_id, customer_id, total));
		LOGGER.info("order updated");
		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
		String answer = getInput();
		if (answer.toLowerCase().equals("return")) {
			Ims newIms = new Ims();
			newIms.imsSystem();
		}
		return null;

	}

	@Override
	public void delete() {
		LOGGER.info("PLease enter the id of the order you would like to delete?");
		orderService.delete(Long.valueOf(getInput()));
		LOGGER.info("order deleted");
		LOGGER.info("type 'RETURN' if you would like to go back to the options menu");
		String answer = getInput();
		if (answer.toLowerCase().equals("return")) {
			Ims newIms = new Ims();
			newIms.imsSystem();
		}

	}

}
