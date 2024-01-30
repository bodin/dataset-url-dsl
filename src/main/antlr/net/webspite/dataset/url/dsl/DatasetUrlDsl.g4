grammar DatasetUrlDsl;

@header {
package net.webspite.dataset.url.dsl;
}

parse : root EOF;

root
    : filter root
    | sort root
    | page root
    | /* empty */
    ;

filter
    : 'filter' '(' or_expr ')'
    ;

or_expr
    : or_expr_ne
    | /* empty */
    ;

or_expr_ne
    : and_expr 'or' or_expr_ne
    | and_expr
    ;

and_expr
    : filter_term 'and' and_expr
    | filter_term
    ;

filter_term
    : filter_term_eq
    | filter_term_starts_with
    | filter_term_ends_with
    | filter_term_contains
    | filter_term_not
    | filter_term_nested
    ;


filter_term_eq: 'eq' '(' ID ',' literal ')';
filter_term_starts_with: 'starts_with' '(' ID ',' literal ')';
filter_term_ends_with: 'ends_with' '(' ID ',' literal ')';
filter_term_contains: 'contains' '(' ID ',' literal ')';
filter_term_not: 'not' '(' or_expr_ne ')';
filter_term_nested: '(' or_expr_ne ')';

sort
    : 'sort' '(' sort_exprs ')'
    ;

sort_exprs
    : sort_exprs_ne
    | /* empty */
    ;

sort_exprs_ne
    : sort_expr ',' sort_exprs_ne
    | sort_expr
    ;

sort_expr
    : ID sort_options
    ;

sort_options
    : sort_option sort_options
    | /* empty */
    ;

sort_option
    : sort_option_asc
    | sort_option_desc
    | sort_option_nulls_first
    | sort_option_nulls_last
    | sort_option_alphanumeric
    ;

sort_option_asc : 'asc';
sort_option_desc : 'desc';
sort_option_nulls_first : 'nulls' 'first';
sort_option_nulls_last : 'nulls' 'last';
sort_option_alphanumeric : 'alphanumeric';


literal
    : ID
    | number
    | 'true'
    | 'false'
    ;

page
    : 'page' '(' number_node=INT 'by' size_node=INT ')'
    ;

number
    : DECIMAL
    | INT
    ;

ID : [a-zA-Z]+[a-zA-Z0-9\-_]* ;
INT : [0-9]+ ;
DECIMAL : [0-9]*[.][0-9]+ ;

WS: [ \t\r\n]+ -> skip;