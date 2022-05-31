<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Departement extends Model
{
    use HasFactory;
    protected $table='departements';
    protected $fillable = [
        "titre",

    ];

    public function salles(){
        return $this->hasMany(Salle::class);
    }

    public function user(){
        return $this->hasOne(User::class,'id','chef_departement_id');
    }

    public function classes(){
        return $this->hasMany(Classe::class);
    }


}
