<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotel.reservation.room.model.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>"
              type="text/css" />
        <title>Modify Room</title>
    </head>
    <body>
        <div id="main_wrapper">

            <h1>Modify Room</h1>

            <%
                    Room room = (Room) request.getAttribute("room");
            %>

            <form method="post" action="../modify">
                <input type="hidden" name="id" id="id"
                       value="<%=room.getId()%>" />
                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>Floor No.</td>
                                    <td><input type="text" style="width: 185px;" maxlength="5" name="floorNo"
                                        id="floorNo" value="<%=room.getFloorNo()%>" /></td>
                                </tr>
                                <tr>
                                    <td>Room No.</td>
                                    <td><input type="text" style="width: 185px;" maxlength="5"
                                               name="roomNo" id="roomNo" value="<%=room.getRoomNo()%>" /></td>
                                </tr>
                                <tr>
                                    <td>Room Type</td>
                                    <td><select name="roomType">
                                            <%
                                                    if (room.getRoomType() == 1) {
                                            %>
                                            <option value="1" selected="selected">Studio</option>
                                            <option value="2">Deluxe</option>
                                            <option value="3">Executive</option>
                                            <%
                                                    } else if (room.getRoomType() == 2) {
                                            %>
                                            <option value="1">Studio</option>
                                            <option value="2" selected="selected">Deluxe</option>
                                            <option value="3">Executive</option>
                                            <%
                                                    } else if (room.getRoomType() == 3) {
                                            %>
                                            <option value="1">Studio</option>
                                            <option value="2">Deluxe</option>
                                            <option value="3" selected="selected">Executive</option>
                                            <%
                                                    }
                                            %>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Reserved:</td>
                                    <td><select name="reserved">
                                            <%
                                                    if (room.isReserved() == true) {
                                            %>
                                            <option value="Y" selected="selected">Reserved</option>
                                            <option value="N">No</option>
                                            <%
                                                    } else {
                                            %>
                                            <option value="Y" >Reserved</option>
                                            <option value="N" selected="selected">No</option>
                                            <%
                                                    }
                                            %>
                                        </select></td>
                                </tr>
                            </table> <input type="submit" class="update" title="Update" value="Update" />
                        </td>
                    </tr>
                </table>
            </form>

        </div>

    </body>
</html>
