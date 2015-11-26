<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <link rel="stylesheet" href="/styles/bootstrap.min.css" type="text/css"/>
        <link href="/styles/navbar.css" rel="stylesheet">
        <link href="/styles/jumbotron-narrow.css" rel="stylesheet">
        <title>Home</title>
    </head>
    <body>

        <div class="container">
              <div class="header clearfix">
                <nav>
                  <ul class="nav nav-pills pull-right">
                    <li role="presentation" class="active"><a href="/hotel">Home</a></li>
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

              <br/>




        </div>
        <div id="main_wrapper" align="center">

                    <h1>Welcome to Hotel Reservation System</h1>


                    <h2>This is the homepage of the Hotel Reservation System <br/> prepared by K R Ganeshrajhan.</h2>

                    <h3></h3>


        </div>





    <!-- Placed at the end of the document so the pages load faster -->
        <script src="/scripts/jquery.min.js"></script>
        <script src="/scripts/bootstrap.min.js"></script>
        <script src="/scripts/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>