<%@ taglib prefix="c" uri="/taglibs/jstl-core.tld"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>"
              type="text/css" />
        <title>Create Reservation</title>

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

            <h1>Create Reservation</h1>

            <form method="post" action="create">

                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>Room No.</td>
                                    <td><input type="text" style="width: 185px;" maxlength="5"
                                               name="roomNo" id="roomNo" /></td>
                                </tr>
                                <tr>
                                    <td>Booking Date</td>
                                    <td><input type="text" maxlength="10" name="reservedDate"
                                                id="reservedDate" placeholder="Booking Date"></td>
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