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
        <title>Add a Room</title>
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
                                            <li role="presentation"><a href="/hotel/room/list">List Room</a></li>
                                            <li role="presentation" class="active"><a href="/hotel/room/addRoom">Add Room</a></li>
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

            <h1>Add Room</h1>

            <form method="post" action="create">

                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>Floor No.</td>
                                    <td><input type="text" style="width: 185px;" maxlength="5"
                                               name="floorNo" id="floorNo" /></td>
                                </tr>
                                <tr>
                                    <td>Room No.</td>
                                    <td><input type="text" style="width: 185px;" maxlength="5"
                                               name="roomNo" id="roomNo" /></td>
                                </tr>
                                <tr>
                                    <td>Room Type</td>
                                    <td><select name="roomType">
                                        <option value="1" selected="selected">Studio</option>
                                        <option value="2">Deluxe</option>
                                        <option value="3">Executive</option>
                                    </select></td>
                                </tr>

                                <tr>
                                    <td>Reserved:</td>
                                    <td><select name="reserved">
                                            <option value="N" selected="selected">No</option>
                                            <option value="Y">Reserved</option>
                                        </select></td>
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
        <!-- Placed at the end of the document so the pages load faster -->
                <script src="/scripts/jquery.min.js"></script>
                <script src="/scripts/bootstrap.min.js"></script>
                <script src="/scripts/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>