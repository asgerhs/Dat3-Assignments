/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function add(n1, n2){
   return n1 +n2;
}

var sub = function(n1,n2){
  return n1 - n2
} 

function mul(n1, n2){
    return n1 * n2;
}

var cb = function(n1,n2, callback){
  try{
  if(typeof n1 === "number" && typeof callback === "function" )
  return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
  }catch(e){
      console.log("you must provide two numbers and which function you want to use.");
  }
};

console.log( add(1,2) )     // What will this print?
// will print 3

console.log( add )          // What will it print and what does add represent?
// will just print function add, as we have not given it any parameteres

console.log( add(1,2,3) ) ; // What will it print
// will print 3, discarding the third argument.

console.log( add(1) );	  // What will it print 
// NaN, doesn't have enough parameters to give an answer

console.log( cb(3,3,add) ); // What will it print
// will print the text form cb, and add the two number. (Result from the two nubmers 
//3 + 3 = 6;

console.log( cb(4,3,sub) ); // What will it print
//same as above, here it will although say 4+3=1, this is because we call the subtract method.

console.log(cb(3,3,add())); // What will it print (and what was the problem)
//since we haven't putten any parameters, it sees callback as a function. 
// since we don't have a function called callback, we get an error. 

console.log(cb(3,"hh",add));// What will it print
// will print 3hh, as we're adding 3 with hh. 

console.log(cb(3, 3, mul));

console.log(cb(3, 3, div = function(n1, n2){ return n1/n2}));


var names = ["Bo", "Vikke", "Martin", "Ole", "William"];

var filter = names.filter(names => names.length <=3);

console.log(filter);

var mapped = filter.map(x => String(x).toUpperCase());

console.log(mapped);

var html = "<ul>" + names.map(x => "<li>" + x + "</li>").join(' ') + "</ul>"
console.log(html);




var cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

console.log(cars.filter(year => year.year >1999));

console.log(cars.filter(make => make.make === 'Volvo'));

console.log(cars.filter(price => price.price < 5000));


var query = cars.map( x => "INSERT INTO cars (id,year,make,model,price) VALUES (" + x.id + ', '+ x.year +", "+ x.make +", "+ x.model +", "+ x.price + ")").join(";") + ";";

console.log(query);




//Those without delay as a parameter will print instantly, and those with delay will print afterwards.
//var msgPrinter = function(msg,delay){
//  setTimeout(function(){
//    console.log(msg);
//  },delay);
//};
//console.log("aaaaaaaaaa");
//msgPrinter ("bbbbbbbbbb",2000);
//console.log("dddddddddd");
//msgPrinter ("eeeeeeeeee",1000);
//console.log("ffffffffff");


//function Person(name){
//  this.name = name;
//  console.log("Name: "+ this.name);
//  setTimeout(function(){
//    console.log("Hi  "+this.name);  //Explain this
//  },2000);
//}

////Store a reference to the outer this
//function Person(name){
//  this.name = name;
//  var self = this;
//  console.log("Name: "+ this.name);
//  setTimeout(function(){
//    console.log("Hi  "+self.name);  
//  },2000);
//}


//Using the bind(..) function
function Person(name){
  this.name = name;  
  console.log("Name: "+ this.name);
  setTimeout(function(){
    console.log("Hi  "+this.name);  
  }.bind(this),2000);
}


//////call it like this (do it, even if you know it’s silly ;-)
//Person("Kurt Wonnegut");  //This calls the function
//console.log("I'm global: "+ name);  //Explain this
//
//var p = new Person("Kurt Wonnegut");  //Create an instance using the constructor function
//console.log("I'm global: "+ name);  //What’s different ?

//it can't find argument name, causing an error. write p.name and it will work again.



//var greeter = function(){
//  console.log(this.message);
//};
//var comp1 = { message: "Hello World" };
//var comp2 = { message: "Hi" };
//
//var g1 = greeter.bind(comp1 );//We can store a reference, with a specific “this” to use
//var g2 = greeter.bind(comp2 );//And here another “this”
//setTimeout(g1,500);
//setTimeout(g2,1000);




function myPerson(name, birthday, hobby){
    this.name = name;
    this.birthday = birthday;
    this.hobby = hobby;
}

var testPerson = new myPerson("Martin", "11/11/11", "Smoking");

for(var prop in testPerson){
    console.log(prop, testPerson[prop]);
}

delete testPerson.hobby;

for(var prop in testPerson){
    console.log(prop, testPerson[prop]);
}

testPerson["hooby"] = "smoking";

for(var prop in testPerson){
    console.log(prop, testPerson[prop]);
}


function newPerson(firstName, lastName, age){
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    
    this.getDetails = function(){
        return "Firstname: " + firstName + "\n" + "Lastname: " + lastName + "\n" + "age: " + age + ".";
    }
}


var np = new newPerson("Artem", "Ivanov", 666);
console.log(np.getDetails());






