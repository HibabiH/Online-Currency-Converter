# Online-Currency-Converter
This is my implementation of Currency converter, that works based on Google Finance (https://www.google.com/finance/).
--------------------------------------------------------------------------------------------------------------------------------
User Guide
--------------------------------------------------------------------------------------------------------------------------------
User should input 2 names of currencies, first currency user wants to convert from, then currency user want to convert to, and, at last user should type amount of money he wants to convert. Currencies must be provided in international format (EUR, USD and so on), register is not important. Amount must be given as floating point number greater or equal to zero.

General Operating Principle
--------------------------------------------------------------------------------------------------------------------------------
The converter receives the names of currencies and the amount of money from the user. The converter refers to the Google Finance page related to the corresponding exchange rate. On this page, the application finds the value of the transfer coefficient, multiplies it by the amount of money transferred by the user and returns it as a floating point number with 4 digits after the decimal point.

Update Log
================================================================
25.05.2023
---------------------------------------
Function that gets exchange rate from Google website was moved to Runner.java class => ExchangeFunction.java wa deleted as unnecessary.
