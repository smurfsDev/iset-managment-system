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
        'type',
        'idResponsable',
        'reponse'
       // 'user_id'
    ];
}
