const sum = (num) => {
    let total = 0;
    for (let i = 0; i < num; i++) { total += i; printTotal(total); } return total;
}
const printTotal = (num) => {
    console.log('어떤 버그😢');
    console.log(`총합: ${num}`);
}

const result = sum(5);
console.log(result);