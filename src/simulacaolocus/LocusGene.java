package simulacaolocus;

import org.jgap.*;
import org.jgap.impl.IntegerGene;

import java.util.StringTokenizer;

/**
 * Created by Tales on 12/13/2014.
 */
public class LocusGene extends BaseGene implements Gene {

    private static final String TOKEN_SEPARATOR = ":";

    private int m_maxNumberOfGene;
    private Integer m_numberOfRate;

    public LocusGene(Configuration a_configuration,int value) throws InvalidConfigurationException {
        super(a_configuration);
        // Make sure the given maximum is non-negative.
        // --------------------------------------------
        if( m_numberOfRate < 0 )
        {
            throw new IllegalArgumentException(
                    "The maximum number of quarters must be non-negative." );
        }
        if(m_numberOfRate == null){
            m_numberOfRate = value;
        }


    }

    public LocusGene(Configuration a_configuration) throws InvalidConfigurationException {
        super(a_configuration);
        // Make sure the given maximum is non-negative.
        // --------------------------------------------
        m_maxNumberOfGene = 9;
    }

    @Override
    public Gene newGene() {
        return null;
    }

    @Override
    protected Gene newGeneInternal() {
        try {
            // We construct the new QuarterGene with the same maximum number
            // of quarters that this Gene was constructed with.
            // -------------------------------------------------------------
            return new LocusGene(getConfiguration(), m_maxNumberOfGene);
        } catch (InvalidConfigurationException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }

    @Override
    public void setAllele(Object o) {
        m_numberOfRate = (Integer) o;
    }

    public void powerAllele(){
        m_numberOfRate++;
    }

    @Override
    public Object getAllele() {
        return m_numberOfRate;
    }

    @Override
    public String getPersistentRepresentation() throws UnsupportedOperationException {
        return new Integer(m_maxNumberOfGene).toString() + TOKEN_SEPARATOR + m_numberOfRate.toString();
    }

    @Override
    public void setValueFromPersistentRepresentation(String a_representation) throws UnsupportedOperationException, UnsupportedRepresentationException {
        // We're expecting to find the maximum number of quarters that this
        // Gene can represent, followed by a colon, followed by the actual
        // number of quarters currently represented.
        // -----------------------------------------------------------------
        StringTokenizer tokenizer = new StringTokenizer( a_representation,
                TOKEN_SEPARATOR );
        // Make sure there are exactly two tokens.
        // ---------------------------------------
        if( tokenizer.countTokens() != 2 )
        {
            throw new UnsupportedRepresentationException(
                    "Unknown representation format: Two tokens expected." );
        }

        try
        {
            // Parse the two tokens as integers.
            // ---------------------------------
            m_maxNumberOfGene = Integer.parseInt( tokenizer.nextToken() );
            m_numberOfRate = new Integer( tokenizer.nextToken() );
        }
        catch( NumberFormatException e )
        {
            throw new UnsupportedRepresentationException(
                    "Unknown representation format: Expecting integer values." );
        }
    }

    @Override
    public void setToRandomValue(RandomGenerator randomGenerator) {
        m_numberOfRate = new Integer(randomGenerator.nextInt(m_maxNumberOfGene) );
    }

    @Override
    public void cleanup() {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }



    @Override
    public void setApplicationData(Object o) {

    }

    @Override
    public Object getApplicationData() {
        return null;
    }

    @Override
    public void setCompareApplicationData(boolean b) {

    }

    @Override
    public boolean isCompareApplicationData() {
        return false;
    }

    @Override
    public double getEnergy() {
        return 0;
    }

    @Override
    public void setEnergy(double v) {

    }

    @Override
    public void setConstraintChecker(IGeneConstraintChecker iGeneConstraintChecker) {

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public int compareTo(Object a_otherQuarterGene) {
        // If the other allele is null, we're bigger.
        // ------------------------------------------
        if( a_otherQuarterGene == null )
        {
            return 1;
        }

        // If our allele is null, then we're either the same as the given
        // QuarterGene if its allele is also null, or less than it if
        // its allele is not null.
        // -------------------------------------------------------------
        if( m_numberOfRate == null )
        {
            if ( ((LocusGene) a_otherQuarterGene).m_numberOfRate == null )
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }

        // Otherwise, we just take advantage of the Integer.compareTo()
        // method.
        // ------------------------------------------------------------
        return m_numberOfRate.compareTo(
                ( (LocusGene) a_otherQuarterGene ).m_numberOfRate );

    }

    @Override
    public String getUniqueID() {
        return null;
    }

    @Override
    public void setUniqueIDTemplate(String s, int i) {

    }

    @Override
    public String getUniqueIDTemplate(int i) {
        return null;
    }

    public boolean equals(Object otherLocusGene){
        return otherLocusGene instanceof LocusGene &&
                compareTo( otherLocusGene ) == 0;
    }

    public int hashCode(){
        return m_numberOfRate;
    }
    @Override
    public Object getInternalValue() {
        return m_numberOfRate;
    }

    @Override
    public void applyMutation(int a_index, double a_percentage) {
        setAllele(getConfiguration().getRandomGenerator().nextInt(m_maxNumberOfGene));
    }


}
