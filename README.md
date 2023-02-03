# Budget App

## Introduction

A budgeting app for completion of the final project for Coding Nomads Java Programming course.

## Overview

The budgeting app runs from the CLI and allows a user to be able to create and login to a profile (with no security verification in this v0.1)
Once logged in a user can add, view and delete items from a monthly budget:
* Income
* Expenses
* Debts
* Debt payments

A user can then run reports to see:
* An overall account summary
    * Current cash balance
    * Current total debt balance
    * Last month PnL
* Future cash balance predictions at monthly intervals
* Future debt balance predictions at monthly intervals
    * Based on the initial balance and payment schedule
    * Based on the current balance and payment schedule
* Status of all debts
    * Up-to-date/in arrears
    * Expected payments vs payments made

## Installation
There is a MYSQL dump file to create the database required for operation (and some test data included)
Compile and run the Main class in controller