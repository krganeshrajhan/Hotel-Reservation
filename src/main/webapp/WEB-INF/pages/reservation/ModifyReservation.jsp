<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="/taglibs/jstl-fmt.tld"%>
<%@ page import="com.hotel.reservation.reservation.model.Reservation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>"
              type="text/css" />
        <title>Change Reservation</title>

        <link rel="stylesheet" href="/styles/jquery-ui.css">
        <script src="/scripts/jquery-1.10.2.js"></script>
        <script src="/scripts/jquery-ui.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#reservedDate').datepicker({
                    dateFormat: "yy-mm-dd",
                    beforeShow: function () {
                        $(".ui-datepicker").css('font-size', 16)
                    }
                });
            });
        </script>
    </head>
    <body>
        <div id="main_wrapper">

            <h1>Change Reservation</h1>

            <%
                    Reservation reservation = (Reservation) request.getAttribute("reservation");
            %>

            <form method="post" action="../modify">
                <input type="hidden" name="id" id="id"
                       value="<%=reservation.getId()%>" />
                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>Room No.</td>
                                    <td><input type="text" style="width: 185px;" maxlength="5"
                                               name="roomNo" id="roomNo" value="<%=reservation.getRoom().getRoomNo()%>" /></td>
                                </tr>
                                <tr>
                                    <td>Booking Date:</td>
                                    <td>
                                        <fmt:formatDate var="formattedDate" type="time" pattern="yyyy-MM-dd"
                                                value="${reservation.reservedDate}" />
                                        <input type="text" style="width: 185px;" maxlength="10"
                                                value="${formattedDate}" name="reservedDate" id="reservedDate" />
                                    </td>
                                </tr>
                            </table> <input type="submit" class="update" title="Update" value="Update" />
                        </td>
                    </tr>
                </table>
            </form>

        </div>

    </body>
</html>
