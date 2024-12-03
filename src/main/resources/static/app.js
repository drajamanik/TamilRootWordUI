var app = angular.module('plunker', ['ngAnimate']);

app.controller('MainCtrl', function($scope, $http, $timeout) {

    $scope.words = [];
    $scope.wordForm = {
        uid: 0,
        sentence: ""
    };

    var emptyCount = 0 ;
    var date = new Date();
    var loadTime = 1000, //Load the data every second
    errorCount = 0, //Counter for the server errors
    loadPromise; //Pointer to the promise created by the Angular $timout service
	
	 $scope.submitWord = function() {
        var method = "";
        var url = "";
        date = new Date();
        $scope.wordForm.uid = date.getTime();
        
        if ($scope.wordForm.uid != 0) {
            method = "POST";
            url = '/HeadWord/a';
        } else {
            method = "PUT";
            url = '/HeadWord';
        }
        console.log("Logger:submitWord() " + $scope.wordForm.uid +":"+method+":"+$scope.wordForm.sentence);
        $http({
            method: method,
            url: '/HeadWord/word/add',
            dataType: 'json',
            //data: angular.toJson($scope.wordForm),
            data: {uid:$scope.wordForm.uid, sentence:$scope.wordForm.sentence},
            //headers: {'Content-Type': 'application/json'}
            headers: { 'Content-Type': 'application/json; charset=UTF-8' }
        }).then(_success, _error);
    };

  function _refreshData() {
	  var param = date.getTime();
	   
	  //console.log("_refreshData:"+param);
	  $http({
          method: 'GET',
          url: '/HeadWord/word/read?uid='+param
      })
      .then(
          function(res) { // success
        	  //console.log("Logger: _refreshData():" + res.status + " : " + res.status+":"+date.getTime());
			  var tempWords = [];
			  tempWords = res.data;
              
			  if(tempWords.length ==0 && emptyCount<=3){
				  console.log("Logger: tempWords:" + tempWords[tempWords.length]);
				  emptyCount = emptyCount +1;
				  $scope.words = res.data;
				  errorCount = 0;
				  nextLoad();
			  // }else if(tempWords.length == 0){
				  //console.log("Next Load Canceled");
				  //cancelNextLoad();
			  }else if(tempWords[tempWords.length-1].key == "Done"){
				  $scope.words = res.data;
				  console.log("Next Load Canceled");
				  cancelNextLoad();
			  }else{
				$scope.words = res.data;
				errorCount = 0;
				nextLoad();
			  }
          }).catch(function(res) { // error
        	  $scope.data = 'Server error';
              console.log("Error: " + res.status + ":"+errorCount+ " : " + res.data);
              nextLoad(++errorCount * 2 * loadTime);
          }
      );
  }

  var cancelNextLoad = function() {
    $timeout.cancel(loadPromise);
  };

  var nextLoad = function(mill) {
    mill = mill || loadTime;
    
    //Always make sure the last timeout is cleared before starting a new one
    cancelNextLoad();
    //loadPromise = $timeout(getData, mill);
    loadPromise = $timeout(_refreshData, mill);
  };


  //Start polling the data from the server
  //getData();
  _refreshData();

  //Always clear the timeout when the view is destroyed, otherwise it will keep polling
  $scope.$on('$destroy', function() {
    cancelNextLoad();
  });

  $scope.data = 'Loading...';
  
  function _success(res) {
	  var tempWords = [];
	  tempWords = res.data;
	  //if( !($scope.words.length==1 && $scope.words[0].key== "Done")){
	  if( !(tempWords.length>0 && tempWords[tempWords.length-1].key == "Done")){
		_refreshData();
		_clearFormData();
	  }else{
		  console.log("Last Success." );
	  }
  }

  function _error(res) {
      var data = res.data;
      var status = res.status;
      var header = res.header;
      var config = res.config;
      alert("Error: " + status + ":" + data);
  }

  // Clear the form
  function _clearFormData() {
      $scope.wordForm.uid = 0;
      //$scope.wordForm.sentence = "";
  };
});