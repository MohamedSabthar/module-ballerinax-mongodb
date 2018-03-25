/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.data.mongodb.actions;

import org.ballerinalang.bre.Context;
import org.ballerinalang.data.mongodb.Constants;
import org.ballerinalang.data.mongodb.MongoDBDataSource;
import org.ballerinalang.data.mongodb.MongoDBDataSourceUtils;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BJSON;
import org.ballerinalang.model.values.BStruct;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.Receiver;
import org.ballerinalang.natives.annotations.ReturnType;

/**
 * {@code Delete} action to delete documents in a collection.
 *
 * @since 0.5.4
 */
@BallerinaFunction(
            orgName = "ballerina",
            packageName = "data.mongodb",
            functionName = "delete",
            receiver = @Receiver(type = TypeKind.STRUCT, structType = "ClientConnector"),
            args = {@Argument(name = "collectionName", type = TypeKind.STRING),
                    @Argument(name = "filter", type = TypeKind.JSON),
                    @Argument(name = "multiple", type = TypeKind.BOOLEAN)
            },
            returnType = { @ReturnType(type = TypeKind.INT) }
        )
public class Delete extends AbstractMongoDBAction {

    @Override
    public void execute(Context context) {
        BStruct bConnector = (BStruct) context.getRefArgument(0);
        String collectionName = context.getStringArgument(0);
        BJSON filter = (BJSON) context.getRefArgument(1);
        Boolean isMultiple = context.getBooleanArgument(0);
        MongoDBDataSource datasource = (MongoDBDataSource) bConnector.getNativeData(Constants.CLIENT_CONNECTOR);
        try {
            long deletedCount = delete(datasource, collectionName, filter, isMultiple);
            context.setReturnValues(new BInteger(deletedCount));
        } catch (Throwable e) {
            context.setReturnValues(MongoDBDataSourceUtils.getMongoDBConnectorError(context, e));
        }
    }
}