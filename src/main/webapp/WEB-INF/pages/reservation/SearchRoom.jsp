<%@ taglib prefix="c" uri="/taglibs/jstl-core.tld"%>
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
        <title>Search Room</title>

    </head>

    <body>

        <div class="container">
              <div class="header clearfix">
                <nav>
                  <ul class="nav nav-pills pull-right">
                    <li role="presentation"><a href="/hotel/">Home</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reservation <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li role="presentation"><a href="/hotel/reservation/list">List Reservation</a></li>
                            <li role="presentation" ><a href="/hotel/reservation/chooseFloor">Create Reservation</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Room <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li role="presentation" ><a href="/hotel/room/list">List Room</a></li>
                            <li role="presentation" ><a href="/hotel/room/addRoom">Add Room</a></li>
                        </ul>
                    </li>
                    <li role="presentation"><a href="/hotel/reservation/getFloor">Search Floor</a></li>
                    <li role="presentation" class="active"><a href="/hotel/reservation/getRoom">Search Room</a></li>
                  </ul>
                </nav>
                <h3 class="text-muted">Hotel Reservation</h3>
              </div>
        </div>
        <div id="main_wrapper" align="center">

            <h1>Select Floor</h1>
            <hr/>
            <form method="post" action="searchRoom">



               <table>
                       <tr>
                           <td>
                               <table>
                                   <tr>
                                    <td>RoomNo:</td>
                                    <hr/>
                                    <td>
                                    <select class="form-control" id="roomNo" name="roomNo">
                                        <c:forEach var="rm" items="${roomsList}">
                                            <c:choose>
                                                <c:when test="${rm.roomNo eq room.roomNo}">
                                                    <option value="${rm.roomNo}" selected="selected">${rm.roomNo}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${rm.roomNo}">${rm.roomNo}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                      </select></td>
                                </tr>
                            </table>
                            <hr/>
                            <input type="submit" class="save" title="Save" value="Submit" />
                            <span style="padding: 0 80px">&nbsp;</span>

                        </td>
                    </tr>
                </table>
            </form>
            <c:if test="${room!=null}">
            <c:choose>
                <c:when test="${room.reserved == true}">
                   <h2>Room ${room.roomNo} already Booked</h2>
                </c:when>
                <c:otherwise>
                    <h2>Room ${room.roomNo} available</h2>
                </c:otherwise>
            </c:choose>

            </c:if>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
                        <script src="/scripts/jquery.min.js"></script>
                        <script src="/scripts/bootstrap.min.js"></script>
                        <script src="/scripts/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>