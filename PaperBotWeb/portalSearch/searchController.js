var search = angular.module('Search', ['ngRoute', 'ui.bootstrap', 'articles.communication', 'xeditable']);


function articlesRouteConfig($routeProvider) {
    $routeProvider.
            when('/launch', {
                controller: LaunchController,
                templateUrl: 'portals.html'
            }).
            otherwise({
                redirectTo: '/launch'
            });
}
search.config(articlesRouteConfig);

search.controller('SearchController', function ($scope) {
    $scope.opened = {};

    $scope.open = function ($event, elementOpened, $index) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened[elementOpened] = !$scope.opened[elementOpened];
    };
});


function LaunchController($scope, articlesCommunicationService, $filter, $q) {

    articlesCommunicationService.getPortalList().then(function (data) {
        $scope.portalList = [];
        for (i = 0; i < data.length; i++) {
            data[i].i = i;
            if (data[i].searchUrlApi != null) {
                data[i].list = 1;
            } else {
                data[i].list = 2;
            }
            $scope.portalList.push(data[i]);
        }
    }).catch(function () {
        $scope.error = 'Error getting portal details';
    });


    $scope.filterPortalList1 = function (portal) {
        return portal.isDeleted !== true && portal.list === 1;
    };
    $scope.filterPortalList2 = function (portal) {
        return portal.isDeleted !== true && portal.list === 2;
    };


    $scope.cancelPortalList = function () {
    };


    $scope.savePortal = function (portal, id) {
        angular.extend(portal, {id: id});
        var list = [];
        list.push(portal);
        articlesCommunicationService.updatePortalList(portal).then(function (data) {
        }).catch(function () {
            $scope.error = 'Error updating portal';
        });
    };

    var canceller = $q.defer();
    $scope.startSearch = function () {
        $scope.executing = true;
        articlesCommunicationService.launchSearch(canceller).then(function (data) {
            $scope.executing = false;

        }).catch(function (response) {
            $scope.executing = false;
        });
        setTimeout(function () {
            $scope.getLogList();
        }, 1000);
    };

    $scope.stopSearch = function () {
        canceller.resolve("user cancelled");
        canceller = $q.defer();
        articlesCommunicationService.stopSearch().then(function (data) {
            $scope.executing = false;
        }).catch(function () {
            $scope.error = 'Error stopping the search';
        });
        setTimeout(function () {
            $scope.getLogList();
        }, 1000);
    };

    articlesCommunicationService.getKeyWordList().then(function (data) {
        $scope.keyWordList = [];
        for (i = 0; i < data.length; i++) {
            data[i].i = i;
            $scope.keyWordList.push(data[i]);
        }
    }).catch(function () {
        console.log('Error5')

        $scope.error = 'Error getting keywords details';
    });
    $scope.saveKeyWordList = function () {
        var idListDelete = [];
        for (var i = $scope.keyWordList.length; i--; ) {
            var keyWord = $scope.keyWordList[i];
            if (keyWord.isDeleted) {
                $scope.keyWordList.splice(i, 1);
                idListDelete.push(keyWord.id);
            }
            // mark as not new 
            if (keyWord.isNew) {
                keyWord.isNew = false;
            }

        }
        articlesCommunicationService.updateKeyWordList($scope.keyWordList).then(function (data) {
        }).catch(function () {
            console.log('Error6')

            $scope.error = 'Error updating keywords';
        });
        if (idListDelete.length > 0) {
            articlesCommunicationService.deleteKeyWordList(idListDelete).then(function (data) {
            }).catch(function () {
                console.log('Error7')
                $scope.error = 'Error removing keywords';
            });
        }
    };
    $scope.deleteKeyWord = function (i) {
        var filtered = $filter('filter')($scope.keyWordList, {i: i});
        if (filtered.length) {
            filtered[0].isDeleted = true;
        }
    };

    $scope.filterKeyWord = function (keyWord) {
        return keyWord.isDeleted !== true;
    };

    $scope.addkeyWord = function () {
        $scope.keyWordList.push({
            i: $scope.keyWordList.length,
            name: '',
            collection: 'Pending evaluation',
            isNew: true
        });
    };

    $scope.cancelKeyWordList = function () {
        for (var i = $scope.keyWordList.length; i--; ) {
            var keyWord = $scope.keyWordList[i];
            // undelete
            if (keyWord.isDeleted) {
                delete keyWord.isDeleted;
            }
            // remove new 
            if (keyWord.isNew) {
                $scope.keyWordList.splice(i, 1);
            }
        }
        ;
    };
    $scope.getLogList = function () {
        articlesCommunicationService.getLogList().then(function (data) {
            $scope.portalLogList = data;
            if ($scope.portalLogList.length > 0 && $scope.portalLogList[0].stop == null) {
                $scope.executing = true;

            } else {
                $scope.executing = false;
            }

        }).catch(function () {
            $scope.error = 'Error getting log details';
        });
    };
    $scope.getLogList();




    $scope.removeArticleDB = function () {
        if (confirm("You are about to erase the database, press OK to confirm otherwise press Cancel")) {
            $scope.cleaning = true;
            articlesCommunicationService.removeAllArticles().then(function (data) {
                $scope.cleaning = false;
            }).catch(function () {
                $scope.cleaning = false;
                $scope.error = 'Error erasing the article DB';
            });
        } else {
        }
    };
}
