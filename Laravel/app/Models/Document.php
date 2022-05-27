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
        'classes_id', 
        'document_categories_id', 
        'file', 
        'idResponsable'
    ];

    public function categorie()
    {
        return $this->belongsTo('App\Models\DocumentCategorie', 'document_categories_id',);
    }

    public function class(){
        return $this->belongsTo('App\Models\Classe', 'classes_id');
    }
    
}
