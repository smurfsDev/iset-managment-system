<?php

namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Document extends Model
{
    use HasFactory;

    protected $table = 'documents';
    protected $fillable = [
        'nom', 
        'class_id', 
        'document_categories_id', 
        'file', 
        'idResponsable'
    ];

    
   

}
