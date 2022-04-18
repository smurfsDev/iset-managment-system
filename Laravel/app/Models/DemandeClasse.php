<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeClasse extends Model
{
    use HasFactory;
    protected $table='demande_classes';
    protected $fillable = [
        "dateEmploi",
        "dateDeRemise",

    ];
    public function classes(){
        return $this->belongsTo(Classe::class,'idClasse');
    }


}
