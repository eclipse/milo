/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


/**
 * Specifies the details used to perform a “raw” or “modified” history read. (see Spec. 1.04 Part 11 Page 32)
 * This structure selects a set of (modified) values from the history database by specifying a time domain for one or more Variables. (see Spec. 1.04 Part 11 Page 31)
 * @author m.gattinger
 *
 */
public class ReadRawModifiedDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean isReadModified;
    protected final DateTime startTime;
    protected final DateTime endTime;
    protected final UInteger numValuesPerNode;
    protected final Boolean returnBounds;

    public ReadRawModifiedDetails() {
        super();
        this.isReadModified = null;
        this.startTime = null;
        this.endTime = null;
        this.numValuesPerNode = null;
        this.returnBounds = null;
    }

    /**
     * Constructor for {@link ReadRawModifiedDetails}. Just saves parameters to this new object.
     *
     * @param isReadModified    TRUE: the Server returns a HistoryData structure for each operation
     *                          FALSE: the Server returns a HistoryModifiedData structure for each operation  (see Spec. 1.04 Part 11 Page 31)
     * @param startTime Beginning of period to read. Set to default value of DateTime.MinValue if no specific start time is specified. (see Spec. 1.04 Part 11 Page 32)
     * @param endTime End of period to read. Set to default value of DateTime.MinValue if no specific end time is specified. (see Spec. 1.04 Part 11 Page 33)
     * @param numValuesPerNode The maximum number of values returned for any Node over the time range. If only one time is specified, the time range shall extend to return this number of values. The default value 0 indicates that there is no maximum. (see Spec. 1.04 Part 11 Page 33)
     * @param returnBounds      A Boolean parameter with the following values:
                                TRUE Bounding Values should be returned
                                FALSE All other cases (see Spec. 1.04 Part 11 Page 33)
     * @see ReadRawModifiedDetails
     */
    public ReadRawModifiedDetails(Boolean isReadModified, DateTime startTime, DateTime endTime, UInteger numValuesPerNode, Boolean returnBounds) {
        super();
        this.isReadModified = isReadModified;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numValuesPerNode = numValuesPerNode;
        this.returnBounds = returnBounds;
    }

    public Boolean getIsReadModified() { return isReadModified; }

    public DateTime getStartTime() { return startTime; }

    public DateTime getEndTime() { return endTime; }

    public UInteger getNumValuesPerNode() { return numValuesPerNode; }

    public Boolean getReturnBounds() { return returnBounds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("IsReadModified", isReadModified)
            .add("StartTime", startTime)
            .add("EndTime", endTime)
            .add("NumValuesPerNode", numValuesPerNode)
            .add("ReturnBounds", returnBounds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadRawModifiedDetails> {

        @Override
        public Class<ReadRawModifiedDetails> getType() {
            return ReadRawModifiedDetails.class;
        }

        @Override
        public ReadRawModifiedDetails decode(UaDecoder decoder) throws UaSerializationException {
            Boolean isReadModified = decoder.readBoolean("IsReadModified");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            UInteger numValuesPerNode = decoder.readUInt32("NumValuesPerNode");
            Boolean returnBounds = decoder.readBoolean("ReturnBounds");

            return new ReadRawModifiedDetails(isReadModified, startTime, endTime, numValuesPerNode, returnBounds);
        }

        @Override
        public void encode(ReadRawModifiedDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBoolean("IsReadModified", value.isReadModified);
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeDateTime("EndTime", value.endTime);
            encoder.writeUInt32("NumValuesPerNode", value.numValuesPerNode);
            encoder.writeBoolean("ReturnBounds", value.returnBounds);
        }
    }

}
