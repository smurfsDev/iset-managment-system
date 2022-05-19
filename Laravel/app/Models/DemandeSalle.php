<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeSalle extends Model
{
    use HasFactory;
    protected $table='demande_salles';
    protected $fillable = [
        "idSalle",
        "idResponsable",
        "dateEmploi",
        "dateDeRemise",
        "status",
        "reponse",

    ];
    public function salles(){
        return $this->belongsTo(Salle::class,'idSalle');
    }
    public function responsable(){
        return $this->hasOne(User::class,'id','idResponsable');
    }



}
