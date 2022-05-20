<?php

namespace App\Models;

use App\Models\Classe;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Matiere extends Model
{
    use HasFactory;
    protected $table='matieres';
    protected $fillable = [
        "nom",
        "idClasse"
    ];

    public function classe(){
        return $this->hasOne(Classe::class,'idClasse');
    }

    public function enseignant(){
        return $this->hasOne(Enseignant::class,'idEnseignant');
    }


    
}
