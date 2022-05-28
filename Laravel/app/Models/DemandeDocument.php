<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeDocument extends Model
{
    use HasFactory;
    protected $table = 'demande_documents';
    protected $fillable = [
    'titre', 
    'description',
    'document_categories_id', 
    'idResponsable',
    'file',
    'reponse'
   
];

    public function responsable()   
    {
        return $this->belongsTo('App\Models\User', 'idResponsable');
    }

    public function categorie()   
    {
        return $this->belongsTo('App\Models\DocumentCategorie', 'document_categories_id');
    }

}
