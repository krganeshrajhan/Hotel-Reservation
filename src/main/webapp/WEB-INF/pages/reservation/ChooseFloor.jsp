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
        <title>Choose Floor</title>

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

        <div class="container">
                      <div class="header clearfix">
                        <nav>
                          <ul class="nav nav-pills pull-right">
                            <li role="presentation"><a href="/hotel/">Home</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reservation <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li role="presentation" ><a href="/hotel/reservation/list">List Reservation</a></li>
                                    <li role="presentation" class="active"><a href="/hotel/reservation/chooseFloor">Create Reservation</a></li>
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
                            <li role="presentation"><a href="/hotel/reservation/getRoom">Search Room</a></li>
                          </ul>
                        </nav>
                        <h3 class="text-muted">Hotel Reservation</h3>
                      </div>
                </div>
        <div id="main_wrapper" align="center">

            <h1>Choose Floor</h1>

            <form method="post" action="searchRooms">

                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>FloorNo:</td>
                                    <td><select name="floorNo">
                                            <option value="1" selected="selected">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Booking Date</td>
                                    <td><input type="text" maxlength="10" name="reservedDate"
                                                id="reservedDate" placeholder="Booking Date"></td>
                                </tr>
                                    <td>No. of People</td>
                                    <td><input type="text" maxlength="10" name="capacity"
                                                id="capacity"></td>
                                <tr>
                                </tr>
                            </table>

                            <input type="submit" class="save" title="Save" value="Save" />
                            <span style="padding: 0 80px">&nbsp;</span>
                            <input type="button" value="Back" onclick="javascript:history.go(-1)">
                            <a href="/"><input type="button" value="Home"></a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

            <script src="/scripts/bootstrap.min.js"></script>
                            <script src="/scripts/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>