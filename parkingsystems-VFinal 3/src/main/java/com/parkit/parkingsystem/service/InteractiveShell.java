package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * this class configure user interaction
 *
 */
public class InteractiveShell {

	private static final Logger logger = LogManager
			.getLogger("InteractiveShell");

	/**
	 * Load console interface
	 */
	public static void loadInterface() {
		logger.info("App initialized!!!");
		System.out.println("Welcome to Parking System! Parking is free for the first 30 minutes");

		boolean continueApp = true;
		InputReaderUtil inputReaderUtil = new InputReaderUtil();
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
		TicketDAO ticketDAO = new TicketDAO();
		ParkingService parkingService = new ParkingService(inputReaderUtil,
				parkingSpotDAO, ticketDAO);

		while (continueApp) {
			loadMenu();
			int option = inputReaderUtil.readSelection();
			switch (option) {
				case 1 : {
					parkingService.processIncomingVehicle();
					break;
				}
				case 2 : {
					parkingService.processExitingVehicle();
					break;
				}
				case 3 : {
					System.out.println("Exiting from the system!");
					continueApp = false;
					break;
				}
				default :
					System.out.println(
							"Unsupported option. Please enter a number corresponding to the provided menu");
			}
		}
	}

	/**
	 * load message for user
	 */
	private static void loadMenu() {
		System.out.println("");
		System.out.println(
				"Please select an option. Simply enter the number to choose an action");
		System.out.println("1 New Vehicle Entering - Allocate Parking Space");
		System.out.println("2 Vehicle Exiting - Generate Ticket Price");
		System.out.println("3 Shutdown System");
	}

}