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

    public function categorie()
    {
        return $this->belongsTo('App\Models\TypeCat', 'document_categories_id',);
    }

    public function classe()
    {
        return $this->belongsTo('App\Models\Classe', 'class_id');
    }
    
}
