var express = require('express');
var mongo = require('mongoskin');
var jwt = require('jsonwebtoken');
var bodyParser  = require('body-parser');
var db = mongo.db("mongodb://gov-auth-db:27017/authdocgov", {native_parser:true});
var app = express();

app.all('*', function(req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    next();
});



var auth = function(req, res, next) {
// check header or url parameters or post parameters for token
  var token =  req.query.token || req.headers['x-access-token'];

  // decode token
  if (token) {

    // verifies secret and checks exp
    jwt.verify(token, 'PGD-EHR', function(err, decoded) {      
      if (err) {
        return res.json({ success: false, message: 'Failed to authenticate token.' });    
      } else {
        // if everything is good, save to request for use in other routes
        req.decoded = decoded;    
        next();
      }
    });

  } else {

    // if there is no token
    // return an error
    return res.status(403).send({ 
        success: false, 
        message: 'No token provided.' 
    });
  }
}


app.get('/authdoctor', function(request, response) {

   var username = request.query.username;
   var password = request.query.password;
    
   console.log("username is" + username);
   console.log("password is" + password);
      
   db.collection('authdoctor').find({'username':username}).toArray(function(err, result) {
                
      if (result)
      {
            if (password == result[0].password)
            {
                var token = jwt.sign({'username':username,'hospital':'GOV', 'clientip' : request.connection.remoteAddress}, 'PGD-EHR', {
                  expiresInMinutes: 60, // expires in 24 hours
                  audience: 'doctor'
                });
                response.json({
                  success: true,
                  message: 'Enjoy your token!',
                    'clientip' : request.connection.remoteAddress,
                  token: token
                });   

                console.log("success");
                response.end();
            }
            else
            {
                 response.json({ success: false, message: 'Authentication failed. Wrong password.'});
                 console.log('Authentication failed. Wrong password.');
                 response.end();
            }
      }
      else
      {
            response.json({success: false, message: 'Authentication failed. User not found.'});
            console.log('Authentication failed. User not found.');
           response.end();
      }    
    });
 
});

app.get('/ehr', auth, function(request, response) {

   var playload = request.decoded;
   response.json(playload);
 
});


app.listen(8080, '0.0.0.0',  function() {
	console.log("listen on 8080");
});
