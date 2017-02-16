/*
 * Copyright 2007 Dan Shellman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cifpay.insurance.iscreen.validators;

import org.iscreen.BaseValidator;
import org.iscreen.ConfigurationException;
import org.iscreen.FailureMessage;
import org.iscreen.SimpleBean;
import org.iscreen.ValidatorContext;

/**
 * This validator verifies that a passed in java.lang.Number is within
 * a certain range. <br />
 * <br />
 * This validator takes two constraints:<br />
 * minimumValue - The smallest number the value can be. <br />
 * maximumValue - The largest number the value can be (-1 means infinite). <br />
 * <br />
 * This validator takes two failure messages:<br />
 * rangeFailure - Failure when the value it outside the range. <br />
 * invalidNumberFailure - Failure when the value is not a java.lang.Number. <br />
 *
 * @author Shellman, Dan
 */
public class NumberRangeValidator extends BaseValidator
{
  private FailureMessage              rangeFailure;
  private FailureMessage              invalidNumberFailure;
  private long                         max = Long.MAX_VALUE;
  private long                         min;

  /**
   * Default constructor.
   */
  public NumberRangeValidator()
  {
  } //end NumberRangeValidator()

  public void validate( ValidatorContext context, Object objectToValidate )
  {
    long                   numberToValidate;

    try
    {
      numberToValidate = getNumber( objectToValidate );
    }
    catch ( ConfigurationException e )
    {
      context.reportFailure( invalidNumberFailure );
      return;
    }

    if ( ( max != -1 && numberToValidate > max ) ||
         numberToValidate < min )
    {
      context.reportFailure( rangeFailure, new Long( numberToValidate ) );
    }
  } //end validate()

  public void setMinimumValue( Long minValue )
  {
    min = minValue.longValue();
  } //end setMinimumValue()

  public Long getMinimumValue()
  {
    return new Long( min );
  } //end getMinimumValue()

  public void setMaximumValue( Long maxValue )
  {
    max = maxValue.longValue();
  } //end setMaximumValue()

  public Long getMaximumValue()
  {
    return new Long( max );
  } //end getMaximumValue()

  public void setRangeFailure( FailureMessage message )
  {
    rangeFailure = message;
    setDefaultFailure( message );
  } //end setRangeFailure()

  public void setInvalidNumberFailure( FailureMessage message )
  {
    invalidNumberFailure = message;
  } //end setInvalidNumberFailure()


// *****
// Protected methods
// *****

  protected long getNumber( Object obj )
  {
    Object          value = ( ( SimpleBean ) obj ).getValue();

    if ( value instanceof Number )
    {
      return ( ( Number ) value ).longValue();
    }
    else
    if ( value instanceof String )
    {
      try
      {
        return Long.parseLong( ( String ) value );
      }
      catch ( NumberFormatException e )
      {
      }
    }

    throw new ConfigurationException( "NumberRangeValidator:  Object is not a number." );
  } //end getNumber()
} //end NumberRangeValidator
