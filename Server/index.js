var express = require('express'),
  app = express(),
  port = process.env.PORT || 3000;

app.listen(port);

console.log('todo list RESTful API server started on: ' + port);


let personsList = [];

function generateJSON(){
  var myObj =  {
    "persons":[
      {"firstName":"Vipul", "familyName":"Lal","id":1},
      {"firstName":"Sandeep", "familyName":"Lal","id":2},
      {"firstName":"Pranav", "familyName":"Lal","id":3 }
    ]
  };

  return myObj;
}

app.get("/persons", (req, res, next) => {
  //res.json(["Tony","Lisa","Michael","Ginger","Food"]);
  var retValue = generateJSON();
  console.log("Received request\n" + req.params );
  console.log("\nSending response\n" + retValue );
  res.send( retValue );
  console.log("\nReady to receive more requests\n")
 });


