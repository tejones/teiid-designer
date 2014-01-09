/* Generated By:JJTree: Do not edit this line. ArrayTable.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import java.util.ArrayList;
import java.util.List;
import org.teiid.runtime.client.lang.TeiidNodeFactory.ASTNodes;
import org.teiid.runtime.client.lang.parser.TeiidParser;

public class ArrayTable extends FromClause {

    private Expression arrayValue;

    private List<ProjectedColumn> columns = new ArrayList<ProjectedColumn>();

    private GroupSymbol symbol;

    public ArrayTable(int id) {
        super(id);
    }

    public ArrayTable(TeiidParser p, int id) {
        super(p, id);
    }

    public List<ProjectedColumn> getColumns() {
        return columns;
    }
    
    public void setColumns(List<ProjectedColumn> columns) {
        this.columns = columns;
    }
    
    public Expression getArrayValue() {
        return arrayValue;
    }
    
    public void setArrayValue(Expression arrayValue) {
        this.arrayValue = arrayValue;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.symbol.getName();   
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.symbol = this.parser.createASTNode(ASTNodes.GROUP_SYMBOL);
        this.symbol.setName(name);
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=f81b782039749259c07b9501cb43adbf (do not edit this line) */
