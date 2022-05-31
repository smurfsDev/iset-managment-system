<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class EmploieDeTemp extends Model
{
    use HasFactory;
    
    protected $fillable = [
        'title', 
        'classes_id',
        'file',
        'idResponsable',
    ];
    
    public function classes()
    {
        return $this->belongsTo('App\Models\Classe');
    }
}
