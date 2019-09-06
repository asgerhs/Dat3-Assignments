/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//assignment A
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];

//assignment B
var all = boys.concat(girls);
console.log(all);

//assignment C
console.log(all.join());
console.log(all.join('-'));

//assignment D
all.push("Lone", "Gitte");

//assignment E
all.unshift("Hans", "Kurt");

console.log(all);

//assignment F
all.shift();
//assignment G
all.pop();

console.log(all);

//assignment H
all.splice(4,1);
all.splice(3,1);

console.log(all);

//assignment I
all.reverse();
console.log(all);

//assignment J
all.sort();
console.log(all);

//assignment K
 all.sort(function(x,y){ 
      var a = String(x).toUpperCase(); 
      var b = String(y).toUpperCase(); 
      if (a > b) 
         return 1 
      if (a < b) 
         return -1 
      return 0; 
    }); 
    
    console.log(all);

//assignment L

const map1 = all.map(x => String(x).toLocaleUpperCase());

console.log(map1);

//assignment M

var filteredL = all.filter(word1 => String(word1).toUpperCase().startsWith("L"));
var filteredI = all.filter(word2 => String(word2).toUpperCase().startsWith("I"));

var allFilter = filteredL.concat(filteredI);

console.log(allFilter);

