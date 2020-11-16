Temperature Log - documentation
===============================

Table of Contents
-----------------

1.  Introduction
2.  Data storage
3.  How to use Temperature Log?
    1.  Registration
    2.  Accessing station information
    3.  Deleting your station
    4.  Accessing measurements
    5.  Uploading measurements

4.  Author

### 1. Introduction

Temperature Log is a web application that allows its users to track
temperature history in their neighborhood. It is intended to be mainly
used automatically by ESP8266 microcontroller, however some features
require manual user's interference. The purpose of the project is
sheerly educational and recreative - by no means commercial.

### 2. Data storage

Be aware that all information you share with Temperature Log will be
publicly available. It is advisable to use special email account for
registration. You have the right to delete your station at any time.
Never reveal your accurate location (i.e. home address) on the website,
use city name instead. Taking the purpose of the project, I cannot
guarantee persistence and availability of the service. Currently, due to
data storage capacity limitation, each station can only successfully
submit 750 HTTP PUT requests totally.

### How to use Temperature Log?

#### 3a. Registration

To register with Temperature Log use the form provided in graphical app. 
You will be asked to provide your station's location (use city, country) and your
email. I strongly advise against using your private email address,
because it will be publicly visible on the website. After submitting a
successful registration an email message will be sent to you. Keep it
safe, as it contains your station ID and API key, which you will need to
authenticate yourself in the service.

#### 3b. Accessing station information

You can access information about the stations being currently
registered. In order to view all stations make a HTTP GET request
against "/api/stations/". To find a specified station use HTTP GET
against "/api/stations/{stationId}", where {stationId} is a unique
station number. All responses are provided in JSON format. If station
cannot be found HTTP 404 code is returned.

#### 3c. Deleting your station

In order to delete your station you will have to provide your station ID
and API key. Send a HTTP DELETE request against
"/api/stations/" with a JSON body of pattern: 

    {
        "credentials":{
            "id":...,
            "apiKey:"..."
        }
    }
If you submit a successful request HTTP 200 (OK) is returned, 
HTTP 403 (Forbidden) if you provide wrong credentials or HTTP 
404 (Not found) if the station ID provided is incorrect.

#### 3d. Accessing measurements

You can only view measurements from a specified station. To do so make a
HTTP GET request against "/api/measurements/{stationId}". All responses
are provided in JSON format. HTTP 404 (not found) code is returned if 
there is no matching station.

#### 3e. Uploading measurements

This functionality is intended to be used by ESP8266. A HTTP PUT request
has to be sent against "/api/measurements". It must include a JSON body
of pattern:


    { 
        "credentials":{
            "id":...,
            "apiKey":"..."
        },
        "measurement":{
            "stationId":...,
            "temperature":...
        }
    }

If you submit a successful request HTTP 200 (OK) is returned, 
HTTP 403 (Forbidden) if you provide wrong credentials or HTTP 
404 (Not found) if the station ID provided is incorrect. 
Data storage limited, see section 2.

### 4. Author

My name is Bartek. Feel free to check my other projects on
[GitHub](https://github.com/bartek-krk).
