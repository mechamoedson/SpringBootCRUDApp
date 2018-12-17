<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>

        <div ui-view></div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="js/lib/jquery.mask.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/ClienteService.js"></script>
        <script src="js/app/ClienteController.js"></script>
        <script src="js/app/plugins.js"></script>
    </body>
</html>