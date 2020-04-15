// Copyright (c) 2020 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

# Holds the properties of an application error.
#
# + message - Specific error message for the error
# + cause - Cause of the error
public type ApplicationErrorDetail record {
    string message;
    error cause?;
};

# Holds the properties of a database error.
#
# + message - Specific error message for the error
# + mongoDBExceptionType - Type of the returned MongoDB exception 
# + cause - Cause of the error
public type DatabaseErrorDetail record {
    string message;
    string mongoDBExceptionType;
    error cause?;
};

// Error reasons.
public const DATABASE_ERROR_REASON = "{ballerina/mongodb}DatabaseError";

# Represents an error caused by an issue related to database accessibility, erroneous queries, constraint violations,
# database resource clean-up, and other similar scenarios.
public type DatabaseError error<DATABASE_ERROR_REASON, DatabaseErrorDetail>;

public const APPLICATION_ERROR_REASON = "{ballerina/mongodb}ApplicationError";

# Represents an error originating from application-level causes.
public type ApplicationError error<APPLICATION_ERROR_REASON, ApplicationErrorDetail>;

# Represents a database or application level error returned from the MongoDB client remote functions.
public type Error DatabaseError|ApplicationError;
