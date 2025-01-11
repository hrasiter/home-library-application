/**
 * Demonstrates various operations on an array of numbers.
 * 
 * - Logs the entire array.
 * - Logs each element of the array individually.
 * - Logs the length of the array.
 * - Iterates over each element in the array using a for loop and logs each element.
 * - Iterates over each element in the array using a for-of loop and logs each element.
 * - Calculates and logs the sum of all elements in the array.
 * - Calculates and logs the average of all elements in the array.
 * - Finds and logs the maximum element in the array.
 */
let array: number[] = [1, 2, 3, 4, 5];
console.log("Array: " + array);

array.push(6);
console.log("Array: " + array);

console.log("Array Element at 0: " + array[0]);
console.log("Array Element at 1: " + array[1]);
console.log("Array Element at 2: " + array[2]);
console.log("Array Element at 3: " + array[3]);

console.log("Array Length: " + array.length);
/**
 * Iterates over each element in the array.
 * 
 * @param i - The current index of the array being processed.
 */
for (let i: number = 0; i < array.length; i++) {
  console.log(`Array Element at ${i}: ${array[i]}`);
}

// Iterates over each element in the array using a for-of loop.
for (let element of array) {
  console.log(`Array Element: ${element}`);
}

// Calculates the sum of all elements in the array and logs it.
let sum: number = 0;
for (let i: number = 0; i < array.length; i++) {
  sum += array[i];
}
console.log(`Array Sum: ${sum}`);

// Calculates the average of all elements in the array and logs it.
let average: number = sum / array.length;
console.log(`Array Average: ${average}`);

// Finds the maximum element in the array and logs it.
let max: number = array[0];
for (let i: number = 1; i < array.length; i++) {
  if (array[i] > max) {
    max = array[i];
  }
}
console.log(`Array Max: ${max}`);