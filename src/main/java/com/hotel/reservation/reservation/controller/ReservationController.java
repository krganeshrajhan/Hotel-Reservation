package com.hotel.reservation.reservation.controller;

import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.reservation.model.ReservationConst;
import com.hotel.reservation.reservation.service.ReservationService;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.controller
 * Created by: krganeshrajhan
 * Description:
 */
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    private final String REQUEST_MAP = "reservation";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public ReservationService getReservationService() {
        return reservationService;
    }

    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }


    @RequestMapping(value = "/chooseFloor", method = RequestMethod.GET)
    public String getSelectFloor(ModelMap model) {

        return REQUEST_MAP + "/ChooseFloor";
    }

    @RequestMapping(value = "/addReservation", method = RequestMethod.GET)
    public String getAddReservation(ModelMap model) {

        return REQUEST_MAP + "/CreateReservation";
    }

    @RequestMapping(value = "/searchRooms", method = RequestMethod.POST)
    public String searchRooms(@RequestParam("floorNo") String floorNo,
                                          @RequestParam("reservedDate") String reservedDate,
                                          @RequestParam("capacity") String capacity, ModelMap model) throws ParseException {

        List<Room> roomsAvailable = reservationService.getAvailableRooms(reservedDate);

        int requiredCapacity = Integer.parseInt(capacity);
        int availableCapacity = 0;
        int[] floorCapacity = new int[ReservationConst.TOTAL_FLOORS];
        for(Room room: roomsAvailable) {
            availableCapacity = availableCapacity + room.getCapacity();
            floorCapacity[room.getFloorNo()-1] = floorCapacity[room.getFloorNo()-1] + room.getCapacity();

        }
        System.out.println("Size" +roomsAvailable.size());
        System.out.println("Cap"+availableCapacity);
        if(requiredCapacity>availableCapacity) {
            return REQUEST_MAP + "/CannotCheckin";
        } else if (requiredCapacity > floorCapacity[Integer.parseInt(floorNo)-1]) {
            model.addAttribute("floorNo", floorNo);
            model.addAttribute("reservedDate", reservedDate);
            model.addAttribute("capacity", capacity);
            return REQUEST_MAP + "/MultipleFloors";
        } else {

            int[] typeAvailable = new int[3];
            List<Room> roomsByFloor = roomService.getRoomsByFloorNo(floorNo);
            List<Room> roomsAvail = new ArrayList<Room>();
            for(Room room: roomsAvailable) {
                if(room.getFloorNo()==Integer.parseInt(floorNo)) {
                    roomsAvail.add(room);
                }

            }
            for(Room room: roomsAvail) {
                System.out.println("Roomno "+room.getRoomNo()+"roomType "+room.getRoomType());
                typeAvailable[room.getRoomType() - 1] = typeAvailable[room.getRoomType() - 1] + 1;
            }
            List<Reservation> reservations = new ArrayList<Reservation>();
            int cap = requiredCapacity;
            int requiredType = 1;
            System.out.println(typeAvailable[0]+" "+typeAvailable[1]+" "+typeAvailable[2]);
            while (cap>0) {
                System.out.println(typeAvailable[0]+" "+typeAvailable[1]+" "+typeAvailable[2]);
                if((typeAvailable[2]>=1) && (cap>=4)) {
                    requiredType=3;
                } else if((typeAvailable[1]>=1) && (cap>=2)) {
                    requiredType=2;
                } else {
                    requiredType=1;
                }
                Room reservedRoom = null;
                int size = roomsAvailable.size();
                System.out.println("Size "+size);
                System.out.println("Type "+requiredType);
                for(Room room: roomsAvailable) {
                    if((room.getRoomType()==requiredType) ) {
                        Reservation reservation = populateReservation(new Integer(room.getRoomNo()).toString(),
                                reservedDate);
                        String id = reservationService.createReservation(reservation);
                        reservations.add(reservation);
                        reservedRoom = room;
                        cap = cap - room.getCapacity();
                        typeAvailable[requiredType-1] = typeAvailable[requiredType-1] - 1;
                        break;
                    }
                }
                roomsAvailable.remove(reservedRoom);
                System.out.println("Capacity "+cap);
            }
            model.addAttribute("reservationList", reservations);

            return REQUEST_MAP + "/ListReservation";
        }

        /*if(reservationService.isReservationAvailable(roomNo,reservedDate)) {
            reservationService.createReservation(reservation);

            return new ModelAndView("redirect:list");
        } else {
            return new ModelAndView(REQUEST_MAP + "/CannotCheckin");
        }*/



    }

    @RequestMapping(value = "/searchOtherFloors", method = RequestMethod.POST)
    public String searchOtherFloors(@RequestParam("floorNo") String floorNo,
                              @RequestParam("reservedDate") String reservedDate,
                              @RequestParam("capacity") String capacity, @RequestParam("choice") String choice, ModelMap model) throws ParseException {

        List<Room> roomsAvailable = reservationService.getAvailableRooms(reservedDate);
        int[] typeAvailable = new int[3];
        int requiredCapacity = Integer.parseInt(capacity);
        int availableCapacity = 0;
        int[] floorCapacity = new int[ReservationConst.TOTAL_FLOORS];
        for(Room room: roomsAvailable) {
            availableCapacity = availableCapacity + room.getCapacity();
            floorCapacity[room.getFloorNo()-1] = floorCapacity[room.getFloorNo()-1] + 1;
            typeAvailable[room.getRoomType() - 1] = typeAvailable[room.getRoomType() - 1] + 1;
        }

        if(Integer.parseInt(choice)==0) {
            return REQUEST_MAP + "/CannotCheckin";
        } else {

            List<Reservation> reservations = new ArrayList<Reservation>();
            int cap = requiredCapacity;
            int requiredType = 1;
            while (cap>0) {
                if((typeAvailable[2]>=1) && (cap>=4)) {
                    requiredType=3;
                } else if((typeAvailable[1]>=1) && (cap>=2)) {
                    requiredType=2;
                } else {
                    requiredType=1;
                }
                Room reservedRoom = null;
                for(Room room: roomsAvailable) {
                    if((room.getRoomType()==requiredType)) {
                        Reservation reservation = populateReservation(new Integer(room.getRoomNo()).toString(),
                                reservedDate);
                        String id = reservationService.createReservation(reservation);
                        reservations.add(reservation);
                        reservedRoom = room;
                        cap = cap - room.getCapacity();
                        break;
                    }

                }
                roomsAvailable.remove(reservedRoom);
            }


            model.addAttribute("reservationList", reservations);

            return REQUEST_MAP + "/ListReservation";
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createReservation(@RequestParam("roomNo") String roomNo,
                                          @RequestParam("reservedDate") String reservedDate) throws ParseException {

        Reservation reservation = populateReservation(roomNo, reservedDate);

        reservationService.createReservation(reservation);

        return new ModelAndView("redirect:list");




    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listReservation(ModelMap model) {

        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservationList", reservations);

        return REQUEST_MAP + "/ListReservation";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable String id) {

        reservationService.removeReservation(id);

        return new ModelAndView("redirect:../list");

    }

    @RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
    public String getModifyReservation(@PathVariable String id, ModelMap model) {

        Reservation reservation = reservationService.getReservationById(id);

        model.addAttribute("reservation", reservation);

        return REQUEST_MAP + "/ModifyReservation";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelAndView modify(@RequestParam("id") String id, @RequestParam("roomNo") String roomNo,
                               @RequestParam("reservedDate") String reservedDate) throws ParseException {

        Reservation reservation = populateReservation(roomNo, reservedDate);

        reservationService.replaceReservation(id, reservation);

        return new ModelAndView("redirect:list");
    }

    private Reservation populateReservation(String roomNo, String reservedDate) throws ParseException {

        Reservation reservation = new Reservation();
        reservation.setReservedDate(DATE_FORMAT.parse(reservedDate));
        Room room = roomService.getRoomByRoomNo(Integer.parseInt(roomNo));
        reservation.setRoom(room);
        return reservation;
    }


    @RequestMapping(value="/getFloor", method = RequestMethod.GET)
    public String getFloor(ModelMap model) {

        return REQUEST_MAP + "/SearchFloor";
    }


    @RequestMapping(value = "/searchFloor", method = RequestMethod.POST)
    public String searchFloor(@RequestParam("floorNo") String floorNo, ModelMap model) throws ParseException {

        List<Room> rooms = roomService.getRoomsByFloorNo(floorNo);
        for(Room room: rooms) {
            room.setReserved(true);
        }
        List<Room> reservations = reservationService.getAvailableRooms(DATE_FORMAT.format(new Date()));
        int floor = Integer.parseInt(floorNo);
        Map<Integer,Room> roomMap = new HashMap<Integer, Room>(rooms.size());
        for(Room room : rooms) {
            roomMap.put(room.getRoomNo(), room);
        }

        for(Room room: reservations) {
            if(room.getFloorNo()==floor) {
                Room roomMod = roomMap.get(room.getRoomNo());
                roomMod.setReserved(false);
                roomMap.put(room.getRoomNo(), roomMod);
            }
        }

        model.addAttribute("roomsList", new ArrayList<Room>(roomMap.values()));

        return REQUEST_MAP +"/SearchFloor";
    }

    @RequestMapping(value="/getRoom", method = RequestMethod.GET)
    public String getRoomNo(ModelMap model) throws ParseException{

        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("roomsList", rooms);
        Room room = roomService.getRoomByRoomNo(1);
        boolean isAvailable = reservationService.isReservationAvailable("1",DATE_FORMAT.format(new Date()));
        room.setReserved(!isAvailable);
        model.addAttribute("room", room);
        return REQUEST_MAP + "/SearchRoom";
    }


    @RequestMapping(value = "/searchRoom", method = RequestMethod.POST)
    public String searchRoom(@RequestParam("roomNo") String roomNo, ModelMap model) throws ParseException {

        Room room = roomService.getRoomByRoomNo(Integer.parseInt(roomNo));

        boolean isAvailable = reservationService.isReservationAvailable(Integer.toString(room.getRoomNo()),DATE_FORMAT.format(new Date()));
        room.setReserved(!isAvailable);

        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("roomsList", rooms);
        model.addAttribute("room", room);

        return REQUEST_MAP +"/SearchRoom";
    }

}
