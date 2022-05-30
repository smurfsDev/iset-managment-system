<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Reclamation extends Model
{
    use HasFactory;
    protected $table = 'reclamations';
    protected $fillable = [
        'titre', 
        'description', 
        'type_cats_id',
        'idResponsable',
        'reponse',
        'file'
     
    ];

    public function type()
    {
        return $this->belongsTo('App\Models\TypeCat', 'type_cats_id');
    }
}
