
# 🚀 Rest Assured API Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange)
![RestAssured](https://img.shields.io/badge/RestAssured-5.3.0-green)
![Allure](https://img.shields.io/badge/Reporting-Allure-blue)

A robust, data-driven API automation framework designed to test RESTful services. Built with **Java**, **Rest Assured**, and **TestNG**, featuring detailed **Allure Reports** and integrated **CI/CD** pipelines.

---

## 🏗️ Tech Stack

* **Language:** Java 17
* **Library:** Rest Assured (API Validation)
* **Test Runner:** TestNG
* **Build Tool:** Maven
* **Reporting:** Allure Report
* **CI/CD:** GitHub Actions

---

## 📂 Project Structure

```text
src/test/java
├── base            # BaseTest class (Initializes config & RestAssured)
├── config          # ConfigManager (Reads properties files)
├── tests           # Test Classes (API tests, Data Driven tests)
└── listeners       # TestListener (Captures logs for Allure)

src/test/resources
├── config.properties   # Global configuration (BaseUrl, Environment)
└── testdata            # (Optional) JSON/CSV files for data-driven testing
