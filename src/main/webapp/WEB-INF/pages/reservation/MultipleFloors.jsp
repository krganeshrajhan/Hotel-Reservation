<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Error</title>
    </head>

    <body>
        <div id="main_wrapper">

                    <h1>Cannot accomodate the capacity in the same floor. Do you wish to book to book different floors</h1>

                    <%
                            String floorNo = (String) request.getAttribute("floorNo");
                            String reservedDate = (String) request.getAttribute("reservedDate");
                            String capacity = (String) request.getAttribute("capacity");
                    %>

                     <form method="post" action="searchOtherFloors">
                            <input type="hidden" name="floorNo" id="floorNo" value="<%=floorNo%>" />
                            <input type="hidden" name="reservedDate" id="reservedDate" value="<%=reservedDate%>" />
                            <input type="hidden" name="capacity" id="capacity" value="<%=capacity%>" />

                            <table>
                                <tr>
                                    <td>
                                        <table>
                                            <tr>
                                                <td>Choose</td>
                                            <td><input type="radio" name="choice" value="1">Yes<br>
                                                <input type="radio" name="choice" value="0">No</td>
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
    </body>

</html>