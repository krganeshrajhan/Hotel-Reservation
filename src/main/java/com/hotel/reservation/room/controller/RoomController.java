package com.hotel.reservation.room.controller;

import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.service.RoomService;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.room.controller
 * Created by: krganeshrajhan
 * Description:
 */
//@Controller("roomController")
@RequestMapping("/room")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    private final String REQUEST_MAP = "room";


    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }


    @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
    public String getAddRoom(ModelMap model) {

        return REQUEST_MAP + "/CreateRoom";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createRoom(@RequestParam("floorNo") String floorNo, @RequestParam("roomNo") String roomNo,
                                      @RequestParam("roomType") String roomType, @RequestParam("reserved") String reserved)
            throws ParseException {

        Room room = populateRoom(floorNo, roomNo, roomType, reserved);

        roomService.createRoom(room);

        return new ModelAndView("redirect:list");

    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRoom(ModelMap model) {

        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("roomList", rooms);

        return REQUEST_MAP + "/ListRoom";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable String id) {

        roomService.removeRoom(id);

        return new ModelAndView("redirect:../list");

    }

    @RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
    public String getModifyRoom(@PathVariable String id, ModelMap model) {

        Room room = roomService.getRoomById(id);

        model.addAttribute("room", room);

        return REQUEST_MAP + "/ModifyRoom";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelAndView modify(@RequestParam("id") String id, @RequestParam("floorNo") String floorNo,
                               @RequestParam("roomNo") String roomNo, @RequestParam("roomType") String roomType,
                               @RequestParam("reserved") String reserved) {

        Room room = populateRoom(floorNo, roomNo, roomType, reserved);

        roomService.replaceRoom(id, room);

        return new ModelAndView("redirect:list");
    }

    private Room populateRoom(String floorNo, String roomNo, String roomType, String reserved) {

        Room room = new Room();
        room.setFloorNo(Integer.parseInt(floorNo));
        room.setRoomNo(Integer.parseInt(roomNo));
        room.setRoomType(Integer.parseInt(roomType));
        room.setReserved((reserved.equals("Y") ? true : false));
        return room;
    }
}
