let found: boolean = true;
let grade: number = 88.6;
let firstName: string = "John";
let lastName: string = "Doe";
let middleName: string = "William";
let age: number = 21;

let names: string[] = ["John", "Doe", "William"];
let ages: number[] = [21, 22, 23];
let grades: number[] = [88.6, 89.2, 90.1];
let founds: boolean[] = [true, false, true];

let person: [string, string, number] = ["John", "Doe", 21];
let people: [string, string, number][] = [
  ["John", "Doe", 21],
  ["Jane", "Doe", 22],
  ["William", "Doe", 23],
];

let personObject: { firstName: string; lastName: string; age: number } = {
  firstName: "John",
  lastName: "Doe",
  age: 21,
};

let peopleObject: { firstName: string; lastName: string; age: number }[] = [
  { firstName: "John", lastName: "Doe", age: 21 },
  { firstName: "Jane", lastName: "Doe", age: 22 },
  { firstName: "William", lastName: "Doe", age: 23 },
];

let personObjectLiteral = {
  firstName: "John",
  lastName: "Doe",
  age: 21,
};


let peopleObjectLiteral = [
  { firstName: "John", lastName: "Doe", age: 21 },
  { firstName: "Jane", lastName: "Doe", age: 22 },
  { firstName: "William", lastName: "Doe", age: 23 },
];

printVariables();

function printVariables() {
    console.log("Typescript Sample Types");
    console.log("-------------------------------");
    console.log("found: " + found);
    console.log("Template String print");
    console.log(`found: ${found}`);
    console.log(`grade: ${grade}`);
    console.log("firstName: " + firstName);
    console.log("lastName: " + lastName);
    console.log("middleName: " + middleName);
    console.log("age: " + age);

    console.log("names: " + names);
    console.log("ages: " + ages);
    console.log("grades: " + grades);
    console.log("founds: " + founds);

    console.log("person: " + person);
    console.log("people: " + people);

    console.log("personObject: " + personObject);
    console.log("personObject json: " + JSON.stringify(personObject));

    console.log("peopleObject: " + peopleObject);
    console.log("peopleObject json: " + JSON.stringify(peopleObject));

    console.log("personObjectLiteral: " + personObjectLiteral);
    console.log("peopleObjectLiteral: " + peopleObjectLiteral);
}

