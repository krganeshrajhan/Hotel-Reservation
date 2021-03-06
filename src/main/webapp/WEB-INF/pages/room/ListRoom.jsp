<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hotel.reservation.room.model.Room"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="/styles/bootstrap.min.css" type="text/css"/>
        <link href="/styles/navbar.css" rel="stylesheet">
        <link href="/styles/jumbotron-narrow.css" rel="stylesheet">
        <title>Room List</title>
    </head>

    <body>
        <div class="container">
                      <div class="header clearfix">
                        <nav>
                          <ul class="nav nav-pills pull-right">
                            <li role="presentation"><a href="/hotel">Home</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reservation <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li role="presentation" ><a href="/hotel/reservation/list">List Reservation</a></li>
                                    <li role="presentation" ><a href="/hotel/reservation/chooseFloor">Create Reservation</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Room <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li role="presentation"  class="active"><a href="/hotel/room/list">List Room</a></li>
                                    <li role="presentation" ><a href="/hotel/room/addRoom">Add Room</a></li>
                                </ul>
                            </li>
                            <li role="presentation"><a href="/hotel/reservation/getFloor">Search Floor</a></li>
                            <li role="presentation"><a href="/hotel/reservation/getRoom">Search Room</a></li>
                          </ul>
                        </nav>
                        <h3 class="text-muted">Hotel Reservation</h3>
                      </div>
                </div>

                </div>
                <div id="main_wrapper" align="center">

            <h1>Room List</h1>

            Function : <a href="addRoom">Add Room</a>
            <hr />


            <h2>All Categories</h2>
            <table border="1">
                <thead>
                    <tr>
                        <td>Floor No.</td>
                        <td>Room No</td>
                        <td>Room Type</td>
                        <td>Reserved</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <c:forEach items="${roomList}" var="room">

                    <tr>
                        <td>${room.floorNo}</td>
                        <td>${room.roomNo}</td>
                        <td>${room.roomType}</td>
                        <td>${room.reserved}</td>
                        <td><a href="modify/${room.id}">Modify</a> |<a
                                href="delete/${room.id}">Delete</a></td>
                    </tr>

                </c:forEach>
            </table>
            <br/>
            <a href="/">Home</a>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
                <script src="/scripts/jquery.min.js"></script>
                <script src="/scripts/bootstrap.min.js"></script>
                <script src="/scripts/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>