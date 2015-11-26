package com.hotel.reservation.home.controller;

import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.home.controller
 * Created by: krganeshrajhan
 * Description:
 */
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model, HttpServletRequest request) {

        List<Room> rooms = roomService.getAllRooms();
        if((rooms==null) || (rooms.size()==0) ) {
            createRooms();
        }


        return "home";

    }

    public void createRooms() {
        //Cleanup and recreate data
        int roomNo =0;
        for(int floorNo=1; floorNo<=5; floorNo++) {
            for(int studio=0; studio<2; studio++) {
                Room room = roomService.populateRoom(floorNo, ++roomNo, 1, "N");
                roomService.createRoom(room);
            }
            for(int deluxe=0; deluxe<5; deluxe++) {
                Room room = roomService.populateRoom(floorNo, ++roomNo, 2, "N");
                roomService.createRoom(room);
            }
            for(int executive=0; executive<3; executive++) {
                Room room = roomService.populateRoom(floorNo,++roomNo,3,"N");
                roomService.createRoom(room);
            }
        }
    }




    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
